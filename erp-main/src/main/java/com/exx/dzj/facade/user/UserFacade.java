package com.exx.dzj.facade.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exx.dzj.entity.user.UserInfo;
import com.exx.dzj.entity.user.UserModel;
import com.exx.dzj.entity.user.UserQuery;
import com.exx.dzj.entity.user.UserVo;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.facade.sys.RoleFacade;
import com.exx.dzj.result.Result;
import com.exx.dzj.result.SelectionSaleInfo;
import com.exx.dzj.service.user.UserRoleService;
import com.exx.dzj.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author
 * @Date 2019/1/8 0008 17:04
 * @Description 获取用户信息
 */
@Slf4j
@Component
public class UserFacade {

    @Autowired
    private UserTokenFacade userTokenFacade;

    @Autowired
    private RoleFacade roleFacade;

    @Autowired
    private UserService salesmanService;

    @Autowired
    private UserRoleService userRolesService;

    /**
     * 获取用户信息和用户角色
     * @param userToken
     * @return
     */
    public UserVo getUserRoles(String userToken){
        UserVo userVo = this.getUserInfo(userToken);
        // 查询用户角色
        /*if(null != userVo){
            userVo.setRoles(userRolesService.queryUserRoles(userVo.getUserCode()));
        }*/
        return userVo;
    }

    /**
     * 获取 用户信息
     * @return
     */
    public UserVo getUserInfo(String userToken){
        // 通过 userToken 获取用户信息
        String userCode = userTokenFacade.queryUserCodeForToken(userToken);
        UserInfo info = new UserInfo();
        info.setUserCode(userCode);
        return salesmanService.queryUserInfo(info);
    }

    /**
     * 查询用户信息
     * @param userCode
     * @return
     */
    public UserVo queryUserBean(String userCode) {
        return salesmanService.queryUserBean(userCode);
    }

    /**
     * 保存业务员信息 (将不同服务间的调用放在 facade 层，到时候系统整体拆分更方便)
     * @param bean
     * @return
     */
    @Transactional(rollbackFor = ErpException.class)
    public void saveUserInfo(UserVo bean) throws ErpException{
        try{
            String userCode = salesmanService.saveSalesman(bean);
            if(!CollectionUtils.isEmpty(bean.getRoles())){
                roleFacade.delByUserCode(userCode);
                roleFacade.saveUserRole(userCode, bean.getRoles());
            }
        }catch(ErpException ex){
            log.error("执行方法:{},错误信息:{}", UserFacade.class.getName()+".saveUserInfo", ex.getMessage());
            throw new ErpException(400, "保存用户信息失败!");
        }
    }

    /**
     * @功能: 修改用户信息或密码(个人中心)
     * @param bean
     * @return
     */
    public Result modifyUserInfo(UserVo bean) {
        Result result = Result.responseSuccess();
        try {
            salesmanService.modifyUserInfo(bean);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("修改失败!");
            log.error("执行方法:{},错误信息:{}", UserFacade.class.getName()+".modifyUserInfo", ex.getMessage());
        }
        return result;
    }

    /**
     * @description查询业务员
     * @author yangyun
     * @date 2019/1/15 0015
     * @param
     * @return java.util.List<com.exx.dzj.entity.user.UserInfo>
     */
    public List<UserInfo> querySalesman(){
        return salesmanService.querySalesman();
    }

    public Map<String, UserInfo> querySaleManCodeName (){
        List<UserInfo> userInfos = querySalesman();
        Map<String, UserInfo> userInfoMap = userInfos.stream().collect(Collectors.toMap(UserInfo::getSalesmanCode, u -> u, (k1, k2) -> k1));
        return userInfoMap;
    }

    /**
     * 查询公司员工列表数据
     * @param pageNum
     * @param pageSize
     * @param query
     * @return
     */
    public Result list(int pageNum, int pageSize, UserQuery query) {
        return salesmanService.list(pageNum, pageSize, query);
    }

    public Result queryList(int pageNum, int pageSize, QueryWrapper queryWrapper) {
        return salesmanService.queryList(pageNum, pageSize, queryWrapper);
    }

    /**
     * 检查 用户账号是否被注册
     * @param userName
     * @return
     */
    public Result checkUserName(String userName) {
        return  salesmanService.checkUserName(userName);
    }

    /**
     * 判断当前的业务编码是否有人使用
     * @param salesmanCode
     * @return
     */
    public Result checkSalesmanCode(String salesmanCode) {
        return salesmanService.checkSalesmanCode(salesmanCode);
    }

    /**
     *  用户 离职操作
     * @param userCode
     * @return
     */
    public Result quitUser(String userCode) {
        Result result = Result.responseSuccess();
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserCode(userCode);
            result = salesmanService.quitUser(userInfo);
        } catch(Exception ex) {
            result.setCode(400);
            result.setMsg("操作失败!");
        }
        return result;
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    public UserVo queryUserName(String userName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        return salesmanService.queryUserInfo(userInfo);
    }

    public List<SelectionSaleInfo> selectionUserInfo (){
        List<UserModel> userModels = salesmanService.selectionUserInfo();
        Map<String, Map<String, List<UserModel>>> collect = userModels.stream().collect(Collectors.groupingBy(UserModel::getDeptCode, Collectors.groupingBy(UserModel::getDeptName)));
        List<SelectionSaleInfo> list = new ArrayList<>();
        collect.keySet().stream().forEach(
                deptCode -> {
                    Map<String, List<UserModel>> stringListMap = collect.get(deptCode);
                    stringListMap.keySet().stream().forEach(
                            deptName -> {
                                SelectionSaleInfo s = new SelectionSaleInfo();
                                s.setCode(deptCode);
                                s.setLabel(deptName);
                                List<UserModel> userModels1 = stringListMap.get(deptName);
                                userModels1.stream().forEach(
                                    userModel -> {
                                        SelectionSaleInfo s2 = new SelectionSaleInfo();
                                        s2.setCode(userModel.getUserCode());
                                        s2.setLabel(userModel.getRealName());
                                        s.getChildren().add(s2);
                                    }
                                );
                                list.add(s);
                            }
                    );
                }
        );

        return list;
    }

    public List<UserInfo> querySalesmanList(Integer type){
        return salesmanService.querySalesmanList(type);
    }
}
