package com.exx.dzj.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.dept.DeptInfoBean;
import com.exx.dzj.entity.user.*;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.user.UserInfoMapper;
import com.exx.dzj.page.ERPage;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.DeptService;
import com.exx.dzj.service.user.UserService;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.GenerateSequenceUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2019/1/7 0007 15:13
 * @Description 用户(业务员) service
 */
@Service("salesmanService")
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoMapper userMapper;

    @Autowired
    private DeptService deptService;

    /**
     * 查询 业务员列表
     * @return
     */
    @Override
    public List<UserInfo> querySalesman() {
        return userMapper.querySalesman();
    }

    @Override
    public List<UserInfo> querySalesman2() {
        return userMapper.querySalesman2();
    }

    /**
     * 保存 业务员信息
     * @param bean
     */
    @Override
    @SysLog(operate = "保存或修改用户信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public String saveSalesman(UserVo bean) throws ErpException{
        try{
            String userCode = null;

            // 通过 detpCode 查询 orgCode

            DeptInfoBean deptBean = deptService.queryDeptInfo(bean.getDeptCode());
            if(null != deptBean && StringUtils.isNotBlank(bean.getOrgCode())) {
                bean.setOrgCode(bean.getOrgCode());
            }

            if(null != bean && StringUtils.isBlank(bean.getUserCode())){

                //判断 userCode 是否已存在
                do{
                    //获取 userCode
                    userCode = GenerateSequenceUtil.generateSequenceNo();
                }while(checkUserCode(userCode) > 0);

                bean.setUserCode(userCode);
                userMapper.insertSelective(bean);
            }else{
                userMapper.updateByPrimaryKeySelective(bean);
                userCode = bean.getUserCode();
            }
            return userCode;
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", UserServiceImpl.class.getName()+".saveSalesman", ex.getMessage());
            throw new ErpException(400, "保存业务员信息失败!");
        }
    }

    /**
     * @功能: 修改用户信息或密码(个人中心)
     * @param bean
     */
    @Override
    @SysLog(operate = "修改用户基本信息或密码", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public void modifyUserInfo(UserVo bean) {
        try{
            userMapper.updateByPrimaryKeySelective(bean);
        }catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", UserServiceImpl.class.getName()+".modifyUserInfo", ex.getMessage());
            throw new ErpException(400, "修改信息失败!");
        }
    }

    /**
     * 获取用户信息，MyRealm 的 doGetAuthenticationInfo() 会调用这个方法来做验证
     * @param info
     * @return
     */
    @Override
    public UserVo queryUserInfo(UserInfo info) {
        //UserVo userVo = userMapper.queryUserInfo(info);
        UserVo userVo = userMapper.queryUserBean(info);
        if(null != userVo) {
            //userVo.setPassWord("");
            //userVo.setUserName("");
            if(ConvertUtils.isEmpty(userVo.getHeadImg())) {
                userVo.setHeadImg("https://exx-erp.oss-cn-shenzhen.aliyuncs.com/employee-images/prod/erpdefualtheadimg.png");
            }
        }
        return userVo;
    }

    /**
     * 查询 用户信息，包括用户角色信息(用于前端用户修改时显示)
     * @param userCode
     * @return
     */
    @Override
    public UserVo queryUserBean(String userCode) {
        UserInfo info = new UserInfo();
        info.setUserCode(userCode);
        return userMapper.queryUserBean(info);
    }

    /**
     * 查询用户(公司员工)列表数据
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    @Override
    @SysLog(operate = "查询用户列表", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public Result list(int pageNum, int pageSize, UserQuery query){
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<UserModel> list = userMapper.queryUserList(query);
        ERPage<UserModel> pages = new ERPage<UserModel>(list);
        result.setData(pages);
        return result;
    }

    @Override
    public Result queryList(int pageNum, int pageSize, QueryWrapper queryWrapper) {
        Result result = Result.responseSuccess();
        PageHelper.startPage(pageNum, pageSize);
        List<UserModel> list = userMapper.queryList(queryWrapper);
        ERPage<UserModel> pages = new ERPage<UserModel>(list);
        result.setData(pages);
        list = null;
        return result;
    }

    /**
     * 检查 用户账号是否被注册
     * @param userName
     * @return
     */
    @Override
    public Result checkUserName(String userName) {
        Result result = Result.responseSuccess();
        UserInfoExample example= new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        long count = userMapper.countByExample(example);
        if(count > 0){
            result.setCode(400);
            result.setMsg("该账号已被注册!");
        }
        return result;
    }

    /**
     * 检查 userCode 是否已存在
     * @param userCode
     * @return
     */
    private long checkUserCode(String userCode) {
        UserInfoExample example= new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        criteria.andIsQuitEqualTo(CommonConstant.DEFAULT_VALUE_ONE);
        return userMapper.countByExample(example);
    }

    /**
     * 判断当前的业务编码是否有人使用
     * @param salesmanCode
     * @return
     */
    @Override
    public Result checkSalesmanCode(String salesmanCode) {
        Result result = Result.responseSuccess();
        UserInfoExample example= new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andSalesmanCodeEqualTo(salesmanCode);
        criteria.andIsQuitEqualTo(CommonConstant.DEFAULT_VALUE_ONE);
        long count = userMapper.countByExample(example);
        if(count > 0){
            result.setCode(400);
            result.setMsg("该业务编码已有人使用!");
        }
        return result;
    }

    /**
     * 离职操作
     * @param record
     * @return
     */
    @Override
    @SysLog(operate = "员工离职", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public Result quitUser(UserInfo record) {
        Result result = Result.responseSuccess();
        try {
            record.setIsQuit(CommonConstant.DEFAULT_VALUE_ZERO);
            userMapper.updateByPrimaryKeySelective(record);
        } catch(Exception ex){
            LOGGER.error("异常方法:{}异常信息:{}", UserServiceImpl.class.getName()+".quitUser", ex.getMessage());
            throw ex;
        }
        return result;
    }

    @Cacheable(value="selectionUserInfo", keyGenerator = "myKeyGenerator")
    @Override
    public List<UserModel> selectionUserInfo() {

        return userMapper.selectionUserInfo();
    }

    @Override
    public List<UserInfo> querySalesmanList(Integer type) {
        return userMapper.querySalesmanList(type);
    }

    @Override
    public String querySalesmanDeptCode(String userCode) {
        return userMapper.querySalesmanDeptCode(userCode);
    }

    @Override
    public List<String> querySalesmanDeptCode2(String salesmanCode) {
        return userMapper.querySalesmanDeptCode2(salesmanCode);
    }

    @Override
    public List<UserInfo> querySalemanIdentityInfo(String identity) {
        return userMapper.querySalemanIdentityInfo(identity);
    }


}
