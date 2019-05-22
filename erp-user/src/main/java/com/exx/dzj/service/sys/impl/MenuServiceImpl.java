package com.exx.dzj.service.sys.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exx.dzj.annotation.SysLog;
import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.constant.LogLevel;
import com.exx.dzj.constant.LogType;
import com.exx.dzj.entity.menu.MenuInfo;
import com.exx.dzj.entity.menu.MenuInfoExample;
import com.exx.dzj.entity.menu.MenuTreeBean;
import com.exx.dzj.excepte.ErpException;
import com.exx.dzj.mapper.menu.MenuInfoMapper;
import com.exx.dzj.result.Result;
import com.exx.dzj.service.sys.MenuService;
import com.exx.dzj.unique.DefaultIdGenerator;
import com.exx.dzj.util.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
     *
     * @return
     */
    @Override
    public Map<String, Object> queryMenuTree() {
        try {
            MenuInfoExample example = new MenuInfoExample();
            example.setOrderByClause(" seq ");
            example.createCriteria().andIsEnableEqualTo(CommonConstant.DEFAULT_VALUE_ONE);
            List<MenuInfo> menus = menuMapper.selectByExample(example);
            List<String> menuCodes = new ArrayList<>();
            if (!CollectionUtils.isEmpty(menus)) {
                for (MenuInfo menu : menus) {
                    menuCodes.add(menu.getMenuCode());
                }
            }
            JSONArray jsonArray = new JSONArray();
            this.listConvertJson(jsonArray, menus, null);
            Map<String, Object> menuData = new HashMap<>();
            menuData.put("menuTree", jsonArray);
            menuData.put("menuKeys", menuCodes);
            return menuData;
        } catch (Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", MenuServiceImpl.class.getName() + ".queryMenuTree", ex.getMessage());
            return null;
        }
    }

    /**
     * 将 menu 的 list 数据转为 json 数据
     *
     * @param jsonArray
     * @param menuList
     * @param parentJson
     */
    private void listConvertJson(JSONArray jsonArray, List<MenuInfo> menuList, JSONObject parentJson) {
        for (MenuInfo menu : menuList) {
            if (null == menu) {
                continue;
            }

            String tempPcode = menu.getParentCode();
            JSONObject json = getJSONObject(menu);
            if (null == parentJson && ConvertUtils.isEmpty(tempPcode)) {
                jsonArray.add(json);
                /**
                 * 如果不是叶子节点, 那么将当前节点作为父节点, 递归
                 */
                if (menu.getIsLeaf() == 0) {
                    listConvertJson(jsonArray, menuList, json);
                }
            } else if (null != parentJson && ConvertUtils.isNotEmpty(tempPcode) && tempPcode.equals(parentJson.get("menuCode"))) {
                if (parentJson.containsKey("children")) {
                    parentJson.getJSONArray("children").add(json);
                } else {
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
     *
     * @param menu
     * @return
     */
    private JSONObject getJSONObject(MenuInfo menu) {
        JSONObject json = new JSONObject();
        json.put("menuCode", menu.getMenuCode());
        json.put("label", menu.getMenuName());
        if (menu.getMenuCode().equals(CommonConstant.HOME_MENU)) {
            json.put("disabled", true);
        }
        return json;
    }

    /**
     * 获取 菜单树形结构列表数据
     *
     * @return
     */
    @Override
    public List<MenuTreeBean> queryMenuList() {
        try {
            MenuInfoExample example = new MenuInfoExample();
            example.createCriteria().andIsEnableEqualTo(CommonConstant.DEFAULT_VALUE_ONE);
            List<MenuInfo> menus = menuMapper.selectByExample(example);
            List<MenuTreeBean> treeList = new ArrayList<>();
            this.getTreeList(treeList, menus, null);
            return treeList;
        } catch (Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", MenuServiceImpl.class.getName() + ".queryMenuList", ex.getMessage());
            return null;
        }
    }

    /**
     * 将 list 结构的菜单转为 树形结构的数据
     *
     * @param treeList
     * @param menus
     * @param treeBean
     */
    private void getTreeList(List<MenuTreeBean> treeList, List<MenuInfo> menus, MenuTreeBean treeBean) {
        for (MenuInfo menu : menus) {
            String tempPCode = menu.getParentCode();
            MenuTreeBean tree = new MenuTreeBean(menu);
            if (null == treeBean && ConvertUtils.isEmpty(tempPCode)) {
                treeList.add(tree);
                if (null != tree.getIsLeaf() && tree.getIsLeaf() == CommonConstant.DEFAULT_VALUE_ZERO) {
                    getTreeList(treeList, menus, tree);
                }
            } else if (null != treeBean && ConvertUtils.isNotEmpty(tempPCode) && tempPCode.equals(treeBean.getMenuCode())) {
                treeBean.getChildren().add(tree);
                if (null != tree.getIsLeaf() && tree.getIsLeaf() == CommonConstant.DEFAULT_VALUE_ZERO) {
                    getTreeList(treeList, menus, tree);
                }
            }
        }
    }

    /**
     * 查询 上级菜单下拉框数据
     * @return
     */
    @Override
    public JSONArray queryMenuData() {
        MenuInfoExample example = new MenuInfoExample();
        example.setOrderByClause(" seq ");
        example.createCriteria()
                .andIsEnableEqualTo(CommonConstant.DEFAULT_VALUE_ONE)
                .andMenuTypeNotEqualTo(CommonConstant.DEFAULT_VALUE_TWO);
        List<MenuInfo> menus = menuMapper.selectByExample(example);
        JSONArray jsonArray = new JSONArray();
        this.listConvertJson(jsonArray, menus, null);
        return jsonArray;
    }

    /**
     * 保存 菜单信息
     * @param menuInfo
     * @return
     */
    @Override
    @SysLog(operate = "更新菜单信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    @Transactional(rollbackFor = Exception.class)
    public Result saveMenu(MenuInfo menuInfo) {
        Result result = Result.responseSuccess();
        try {
            if(ConvertUtils.isNotEmpty(menuInfo.getMenuCode())) {
                if(ConvertUtils.isNotEmpty(menuInfo.getParentCode())
                        && ConvertUtils.isNotEmpty(menuInfo.getMenuCode())
                        && menuInfo.getMenuCode().equals(menuInfo.getParentCode())) {
                    result.setCode(400);
                    result.setMsg("不能将自己设置为自己的上级!");
                    return result;
                }
                //父级菜单修改为子级菜单时，需要判断父级菜单的父级不能是子级原本的子级菜单
                MenuInfo menu = new MenuInfo();
                menu.setMenuCode(menuInfo.getParentCode());
                menu.setParentCode(menuInfo.getMenuCode());
                int count = menuMapper.queryOverlap(menu);
                if(count > 0) {
                    result.setCode(400);
                    result.setMsg("父级菜单的上级不能是自己的子级!");
                    return result;
                }
                menuMapper.updateByPrimaryKeySelective(menuInfo);
            } else {
                //获取排序最大值(在并发情况下这种方式并不可取，可以使用自旋锁的方式来解决，但是自旋锁本身是比较耗资源的)
                int seq = menuMapper.queryMaxSeq();
                menuInfo.setSeq(seq + 1);
                //如果一级菜单，那么没有父级
                if(menuInfo.getMenuType() == CommonConstant.DEFAULT_VALUE_ZERO) {
                    menuInfo.setParentCode(null);
                    menuInfo.setIsLeaf(CommonConstant.DEFAULT_VALUE_ZERO);
                }
                //生成唯一编码
                DefaultIdGenerator generator = new DefaultIdGenerator("");
                menuInfo.setMenuCode(generator.next());
                menuMapper.insertSelective(menuInfo);

                //修改上级节点，为非叶子节点
                if(ConvertUtils.isNotEmpty(menuInfo.getParentCode())) {
                    MenuInfo info = new MenuInfo();
                    info.setMenuCode(menuInfo.getParentCode());
                    info.setIsLeaf(CommonConstant.DEFAULT_VALUE_ZERO);
                    menuMapper.updateByPrimaryKeySelective(menuInfo);
                }
            }
        } catch(Exception ex) {
            LOGGER.error("异常方法:{}异常信息:{}", MenuServiceImpl.class.getName() + ".saveMenu", ex.getMessage());
            throw ex;
        }
        return result;
    }

    /**
     * 查询 菜单详细信息
     * @param menuInfo
     * @return
     */
    @Override
    @SysLog(operate = "查询菜单信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    public MenuInfo queryMenuInfo(MenuInfo menuInfo) {
        return menuMapper.selectByPrimaryKey(menuInfo.getMenuCode());
    }

    /**
     * 删除 菜单信息
     * @param menuInfo
     * @return
     */
    @Override
    @SysLog(operate = "删除菜单信息", logType = LogType.LOG_TYPE_OPERATE, logLevel = LogLevel.LOG_LEVEL_INFO)
    @Transactional(rollbackFor = ErpException.class)
    public Result cancelMenu(MenuInfo menuInfo) throws ErpException{
        Result result = Result.responseSuccess();
        try {
            // 修改子级，将子级的 parent_code 设置为 null, 并且将子菜单的菜单类型设置为一级菜单
            MenuInfo menu = new MenuInfo();
            menu.setParentCode("");
            menu.setMenuType(CommonConstant.DEFAULT_VALUE_ZERO);
            MenuInfoExample example = new MenuInfoExample();
            example.createCriteria().andParentCodeEqualTo(menuInfo.getMenuCode());
            menuMapper.updateByExampleSelective(menu, example);

            menuInfo.setIsEnable(CommonConstant.DEFAULT_VALUE_ZERO);
            menuMapper.cancelMenu(menuInfo);
        } catch(Exception ex) {
            LOGGER.error("异常方法{}异常信息{}", MenuServiceImpl.class.getName() + ".cancelMenu", ex.getMessage());
            throw new ErpException(400, "删除菜单失败!");
        }
        return result;
    }
}
