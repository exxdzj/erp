package com.exx.dzj.service.sys.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.dept.DeptBean;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.dept.DeptInfoBeanExample;
import com.exx.dzj.mapper.dept.DeptInfoBeanMapper;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DeptService;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.unique.IdGenerator;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.SerialNumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 15:06
 * @Description  部门
 */
@Component
public class DeptServiceImpl extends ServiceImpl<DeptInfoBeanMapper, DeptInfoBean> implements DeptService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private DeptInfoBeanMapper deptMapper;

    /**
     * 查询 部门列表数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    @SysLog(operate = "查询部门列表", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public ERPage<DeptInfoBean> queryList(int pageNum, int pageSize) {
        DeptInfoBeanExample example = new DeptInfoBeanExample();
        DeptInfoBeanExample.Criteria criteria = example.createCriteria();
        criteria.andIsEnableEqualTo(CommonConstant.DEFAULT_VALUE_ONE);
        PageHelper.startPage(pageNum, pageSize);
        List<DeptInfoBean> depts = deptMapper.selectByExample(example);
        ERPage<DeptInfoBean> pages = new ERPage<>(depts);
        return pages;
    }

    /**
     * 查询 部门下拉框数据
     * @return
     */
    @Override
    @Cacheable(value = "queryDeptList", keyGenerator = "myKeyGenerator")
    public List<DeptInfoBean> queryDeptList() {
        return deptMapper.queryDeptList();
    }

    /**
     * 获取 部门详细信息
     * @param deptCode
     * @return
     */
    @Override
    public DeptInfoBean queryDeptInfo(String deptCode) {
        return deptMapper.selectByPrimaryKey(deptCode);
    }

    /**
     * 保存 部门信息
     * @param dept
     * @return
     */
    @Override
    @SysLog(operate = "更新部门信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public Result saveDeptInfo(DeptInfoBean dept){
        Result result = Result.responseSuccess();
        try {
            if(ConvertUtils.isNotEmpty(dept.getDeptCode())) {
                deptMapper.updateByPrimaryKeySelective(dept);
            } else {
                int maxSeq = deptMapper.queryMaxSeq();
                if(dept.getSeq() == CommonConstant.DEFAULT_VALUE_ZERO || null == dept.getSeq()){
                    dept.setSeq(maxSeq + 1);
                }
                IdGenerator idGenerator = new DefaultIdGenerator("");
                String deptCode = idGenerator.next();

                String[] codeArray = generateOrgCode(dept.getParentCode());
                dept.setOrgCode(codeArray[0]);
                String orgType = codeArray[1];
                dept.setIsCompare(Integer.valueOf(orgType));
                dept.setDeptCode(deptCode);
                deptMapper.insertSelective(dept);
            }
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", DeptServiceImpl.class.getName()+".saveDeptInfo", ex.getMessage());
            throw ex;
        }
        return result;
    }

    /**
     * 查询 部门树形结构数据
     * @return
     */
    @Override
    public JSONArray queryDeptTree(String deptName) {
        DeptInfoBeanExample example = new DeptInfoBeanExample();
        DeptInfoBeanExample.Criteria criteria = example.createCriteria();
        if(ConvertUtils.isNotEmpty(deptName)){
            criteria.andDeptNameLike(deptName);
        }
        criteria.andIsEnableEqualTo(CommonConstant.DEFAULT_VALUE_ONE);
        List<DeptInfoBean> depts = deptMapper.selectByExample(example);
        JSONArray jsonArray = new JSONArray();
        this.listConvertJson(jsonArray, depts, null);
        return jsonArray;
    }

    /**
     * 将 list 数据转为 json 数据
     * @param jsonArray
     * @param depts
     * @param parentJson
     */
    private void listConvertJson(JSONArray jsonArray, List<DeptInfoBean> depts, JSONObject parentJson) {
        if(null != depts && depts.size() == 1) {
            JSONObject json = getJSONObject(depts.get(0));
            jsonArray.add(json);
            return;
        }
        for(DeptInfoBean dept : depts) {
            if(null == dept) {
                continue;
            }
            String tempPCode = dept.getParentCode();
            JSONObject json = getJSONObject(dept);
            if(null == parentJson && ConvertUtils.isEmpty(tempPCode)) {
                jsonArray.add(json);
                if(ConvertUtils.isEmpty(dept.getParentCode())){
                    listConvertJson(jsonArray, depts, json);
                }
            }else if(null != parentJson && ConvertUtils.isNotEmpty(tempPCode) && tempPCode.equals(parentJson.get("deptCode"))){
                if(parentJson.containsKey("children")){
                    parentJson.getJSONArray("children").add(json);
                }else{
                    JSONArray children = new JSONArray();
                    children.add(json);
                    parentJson.put("children", children);
                }
                listConvertJson(jsonArray, depts, json);
            }
        }
    }

    /**
     * 生成 json 对象
     * @param dept
     * @return
     */
    private JSONObject getJSONObject(DeptInfoBean dept) {
        JSONObject json = new JSONObject();
        json.put("deptCode", dept.getDeptCode());
        json.put("label", dept.getDeptName());
        return json;
    }

    /**
     * 删除 部门数据
     * @param dept
     * @return
     */
    @Override
    @SysLog(operate = "删除部门信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public Result delDept(DeptBean dept) {
        Result result = Result.responseSuccess();
        try {
            dept.setIsEnable(CommonConstant.DEFAULT_VALUE_ZERO);
            deptMapper.delDept(dept);
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", DeptServiceImpl.class.getName()+".delDept", ex.getMessage());
            throw ex;
        }
        return result;
    }

    /**
     * 获取 机构编码和部门类型
     * @param parentCode
     * @return
     */
    private String[] generateOrgCode(String parentCode) {
        //update-begin--Author:Steve  Date:20190201 for：组织机构添加数据代码调整
        LambdaQueryWrapper<DeptInfoBean> query = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<DeptInfoBean> query1 = new LambdaQueryWrapper<>();
        String[] strArray = new String[2];
        // 创建一个List集合,存储查询返回的所有SysDepart对象
        List<DeptInfoBean> departList = new ArrayList<>();
        // 定义新编码字符串
        String newOrgCode = "";
        // 定义旧编码字符串
        String oldOrgCode = "";
        // 定义部门类型
        String orgType = "1";

        // 如果是最高级,则查询出同级的org_code, 调用工具类生成编码并返回
        if (StringUtil.isEmpty(parentCode)) {
            // 线判断数据库中的表是否为空,空则直接返回初始编码
            query1.eq(DeptInfoBean::getParentCode, "");
            query1.orderByDesc(DeptInfoBean::getOrgCode);
            departList = this.list(query1);
            if(departList == null || departList.size() == 0) {
                strArray[0] = SerialNumberUtil.getNextYouBianCode(null);
                strArray[1] = "1";
                return strArray;
            }else {
                DeptInfoBean depart = departList.get(0);
                oldOrgCode = depart.getOrgCode();
                orgType = depart.getIsCompare()+"";
                newOrgCode = SerialNumberUtil.getNextYouBianCode(oldOrgCode);
            }
        } else {
            // 反之则查询出所有同级的部门,获取结果后有两种情况,有同级和没有同级
            // 封装查询同级的条件
            query.eq(DeptInfoBean::getParentCode, parentCode);
            // 降序排序
            query.orderByDesc(DeptInfoBean::getOrgCode);
            // 查询出同级部门的集合
            List<DeptInfoBean> parentList = this.list(query);
            // 查询出父级部门
            DeptInfoBean depart = this.queryDeptInfo(parentCode);

            String orgCode = "";

            if(null != depart) {
                // 获取父级部门的Code
                orgCode = depart.getOrgCode();
                // 根据父级部门类型算出当前部门的类型
                if(null != depart.getIsCompare()) {
                    orgType = String.valueOf(Integer.valueOf(depart.getIsCompare()) - 1);
                }
            }

            // 处理同级部门为null的情况
            if (parentList == null || parentList.size() == 0) {
                // 直接生成当前的部门编码并返回
                newOrgCode = SerialNumberUtil.getSubYouBianCode(orgCode, null);
            } else {
                //处理有同级部门的情况
                // 获取同级部门的编码,利用工具类
                String subCode = parentList.get(0).getOrgCode();
                // 返回生成的当前部门编码
                newOrgCode = SerialNumberUtil.getSubYouBianCode(orgCode, subCode);
            }
        }
        // 返回最终封装了部门编码和部门类型的数组
        strArray[0] = newOrgCode;
        strArray[1] = orgType;
        return strArray;
    }
}
