package com.exx.dzj.service.sys.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.entity.menu.MenuInfoExample;
import com.exx.dzj.mapper.menu.MenuInfoMapper;
import com.exx.dzj.service.sys.MenuService;
import com.exx.dzj.util.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date 2019/4/9 0009 11:08
 * @Description 菜单
 */
@Component
public class MenuServiceImpl implements MenuService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuInfoMapper menuMapper;

    /**
     * 菜单 树形结构
     * @return
     */
    @Override
    public Map<String, Object> queryMenuTree() {
        try {
            MenuInfoExample example = new MenuInfoExample();
            example.setOrderByClause(" seq ");
            List<MenuInfo> menus = menuMapper.selectByExample(example);
            List<String> menuCodes = new ArrayList<>();
            if(!CollectionUtils.isEmpty(menus)) {
                for(MenuInfo menu : menus) {
                    menuCodes.add(menu.getMenuCode());
                }
            }
            JSONArray jsonArray = new JSONArray();
            this.listConvertJson(jsonArray, menus, null);
            Map<String, Object> menuData = new HashMap<>();
            menuData.put("menuTree", jsonArray);
            menuData.put("menuKeys", menuCodes);
            return menuData;
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", MenuServiceImpl.class.getName()+".queryMenuTree", ex.getMessage());
            return null;
        }
    }

    /**
     * 将 menu 的 list 数据转为 json 数据
     * @param jsonArray
     * @param menuList
     * @param parentJson
     */
    private void listConvertJson(JSONArray jsonArray, List<MenuInfo> menuList, JSONObject parentJson) {
        for(MenuInfo menu : menuList) {
            /**
             * 如果不是菜单类型，则跳出循环
             */
            if(null == menu) {
                continue;
            }

            String tempPcode = menu.getParentCode();
            JSONObject json = getJSONObject(menu);
            if(null == parentJson && ConvertUtils.isEmpty(tempPcode)) {
                jsonArray.add(json);
                /**
                 * 如果不是叶子节点, 那么将当前节点作为父节点, 递归
                 */
                if(menu.getIsLeaf() == 0){
                    listConvertJson(jsonArray, menuList, json);
                }
            }else if(null != parentJson && ConvertUtils.isNotEmpty(tempPcode) && tempPcode.equals(parentJson.get("menuCode"))){
                if(parentJson.containsKey("children")){
                    parentJson.getJSONArray("children").add(json);
                }else{
                    JSONArray children = new JSONArray();
                    children.add(json);
                    parentJson.put("children", children);
                }
                listConvertJson(jsonArray, menuList, json);
            }
        }
    }

    /**
     * 将 menu 数据对象转为 json 对象
     * @param menu
     * @return
     */
    private JSONObject getJSONObject(MenuInfo menu) {
        JSONObject json = new JSONObject();
        json.put("menuCode", menu.getMenuCode());
        json.put("label", menu.getMenuName());
        if(menu.getMenuCode().equals(CommonConstant.HOME_MENU)) {
            json.put("disabled", true);
        }
        return json;
    }
}
