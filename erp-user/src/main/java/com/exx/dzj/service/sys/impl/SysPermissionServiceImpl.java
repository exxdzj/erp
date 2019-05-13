package com.exx.dzj.service.sys.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.mapper.sys.SysPermissionMapper;
import com.exx.dzj.service.sys.SysPermissionService;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date 2019/3/22 0022 15:10
 * @Description 系统权限
 */
@Service("permissionService")
public class SysPermissionServiceImpl implements SysPermissionService {

    Class clazz;
    private final static Logger LOGGER = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 根据用户角色获取权限
     * @param userCode
     * @return
     */
    @Override
    public JSONArray queryPermissionsByUser(String userCode) {
        try {
            // 获取到菜单权限数据
            List<MenuInfo> menus = sysPermissionMapper.queryPermissionsByUser(userCode);
            JSONArray jsonArray = new JSONArray();
            this.getPermissionJsonArray(jsonArray, menus, null);
            LOGGER.info("权限数据{}", jsonArray);
            return jsonArray;
        } catch (Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", SysPermissionServiceImpl.class.getName()+".queryPermissionsByUser", ex.getMessage());
            return null;
        }
    }

    /**
     *  获取菜单JSON数组
     * @param jsonArray
     * @param metaList
     * @param parentJson
     */
    private void getPermissionJsonArray(JSONArray jsonArray, List<MenuInfo> metaList, JSONObject parentJson) {
        for (MenuInfo permission : metaList) {
            if(permission.getMenuType()==null) {
                continue;
            }
            String tempPid = permission.getParentCode();
            JSONObject json = getPermissionJsonObject(permission);
            if(parentJson==null && ConvertUtils.isEmpty(tempPid)) {
                jsonArray.add(json);
                if(permission.getIsLeaf()==0) {
                    getPermissionJsonArray(jsonArray, metaList, json);
                }
            }else if(parentJson!=null && ConvertUtils.isNotEmpty(tempPid) && tempPid.equals(parentJson.getString("id"))){
                if(permission.getMenuType()==0) {
                    JSONObject metaJson = parentJson.getJSONObject("meta");
                    if(metaJson.containsKey("permissionList")) {
                        metaJson.getJSONArray("permissionList").add(json);
                    }else {
                        JSONArray permissionList = new JSONArray();
                        permissionList.add(json);
                        metaJson.put("permissionList", permissionList);
                    }

                }else if(permission.getMenuType()==1) {
                    if(parentJson.containsKey("children")) {
                        parentJson.getJSONArray("children").add(json);
                    }else {
                        JSONArray children = new JSONArray();
                        children.add(json);
                        parentJson.put("children", children);
                    }

                    if(permission.getIsLeaf()==0) {
                        getPermissionJsonArray(jsonArray, metaList, json);
                    }
                }
            }
        }
    }

    private JSONObject getPermissionJsonObject(MenuInfo permission) {
        JSONObject json = new JSONObject();
        //类型(0：一级菜单 1：子菜单  2：按钮)
        if(permission.getMenuType()==2) {
            json.put("action", permission.getPerms());
            json.put("describe", permission.getMenuName());
        }else if(permission.getMenuType()==0||permission.getMenuType()==1) {
            json.put("id", permission.getMenuCode());
            if(permission.getMenuUrl()!=null&&(permission.getMenuUrl().startsWith("http://")||permission.getMenuUrl().startsWith("https://"))) {
                json.put("path", MD5Util.MD5Encode(permission.getMenuUrl(), "utf-8"));
            }else {
                json.put("path", permission.getMenuUrl());
            }

            //重要规则：路由name (通过URL生成路由name,路由name供前端开发，页面跳转使用)
            json.put("name", urlToRouteName(permission.getMenuUrl()));

            //是否隐藏路由，默认都是显示的
            if(permission.isHidden()) {
                json.put("hidden",true);
            }
            //聚合路由
            if(permission.isAlwaysShow()) {
                json.put("alwaysShow",true);
            }
            json.put("component", permission.getComponent());
            JSONObject meta = new JSONObject();
            meta.put("title", permission.getMenuName());
            if(ConvertUtils.isEmpty(permission.getParentCode())) {
                //一级菜单跳转地址
                json.put("redirect",permission.getRedirect());
                meta.put("icon", ConvertUtils.getString(permission.getIcon(), ""));
            }else {
                meta.put("icon", ConvertUtils.getString(permission.getIcon(), ""));
            }
            if(permission.getMenuUrl()!=null&&(permission.getMenuUrl().startsWith("http://")||permission.getMenuUrl().startsWith("https://"))) {
                meta.put("url", permission.getMenuUrl());
            }
            json.put("meta", meta);
        }

        return json;
    }

    /**
     * 通过URL生成路由name（去掉URL前缀斜杠，替换内容中的斜杠‘/’为-）
     * 举例： URL = /isystem/role
     *     RouteName = isystem-role
     * @return
     */
    private String urlToRouteName(String url) {
        if(ConvertUtils.isNotEmpty(url)) {
            if(url.startsWith("/")) {
                url = url.substring(1);
            }
            url = url.replace("/", "-");
            return url;
        }else {
            return null;
        }
    }
}
