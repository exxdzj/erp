package com.exx.dzj.service.sys.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.dept.DeptBean;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.dept.DeptInfoBeanExample;
import com.exx.dzj.mapper.dept.DeptInfoBeanMapper;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DeptService;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.unique.DefaultIdGeneratorConfig;
import com.exx.dzj.unique.IdGenerator;
import com.exx.dzj.unique.IdGeneratorConfig;
import com.exx.dzj.util.ConvertUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author
 * @Date 2019/4/2 0002 15:06
 * @Description  部门
 */
@Component
public class DeptServiceImpl implements DeptService {

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
    public Result saveDeptInfo(DeptInfoBean dept) {
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
                dept.setDeptCode(deptCode);
                deptMapper.insertSelective(dept);
            }
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", DeptServiceImpl.class.getName()+".saveDeptInfo", ex.getMessage());
            result.setCode(400);
            result.setMsg("操作失败!");
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
    public Result delDept(DeptBean dept) {
        Result result = Result.responseSuccess();
        try {
            dept.setIsEnable(CommonConstant.DEFAULT_VALUE_ZERO);
            deptMapper.delDept(dept);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("删除部门数据失败!");
            LOGGER.error("异常方法:{}异常信息:{}", DeptServiceImpl.class.getName()+".delDept", ex.getMessage());
        }
        return result;
    }
}
