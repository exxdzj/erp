package com.exx.dzj.util;

import com.exx.dzj.entity.datarules.DataPermissionRules;
import com.exx.dzj.entity.user.UserVo;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Date 2019/5/27 0027 14:17
 * @Description 数据权限查询规则容器工具类
 */
public class DataAutorUtils {

    public static final String MENU_DATA_AUTHOR_RULES = "MENU_DATA_AUTHOR_RULES";

    public static final String MENU_DATA_AUTHOR_RULE_SQL = "MENU_DATA_AUTHOR_RULE_SQL";

    public static final String SYS_USER_INFO = "SYS_USER_INFO";

    /**
     * 往链接请求里面，传入数据查询条件
     *
     * @param request
     * @param dataRules
     */
    public static synchronized void installDataSearchConditon(HttpServletRequest request, List<DataPermissionRules> dataRules) {
        @SuppressWarnings("unchecked")
        // 1.先从request获取MENU_DATA_AUTHOR_RULES，如果存则获取到LIST
        List<DataPermissionRules> list = loadDataSearchConditon();
        if (list==null) {
            // 2.如果不存在，则new一个list
            list = new ArrayList<DataPermissionRules>();
        }
        for (DataPermissionRules tsDataRule : dataRules) {
            list.add(tsDataRule);
        }
        // 3.往list里面增量存指
        request.setAttribute(MENU_DATA_AUTHOR_RULES, list);
    }

    /**
     * 获取请求对应的数据权限规则
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static synchronized List<DataPermissionRules> loadDataSearchConditon() {
        return (List<DataPermissionRules>) SpringContextUtils.getHttpServletRequest().getAttribute(MENU_DATA_AUTHOR_RULES);
    }

    /**
     * 获取请求对应的数据权限SQL
     *
     * @return
     */
    public static synchronized String loadDataSearchConditonSQLString() {
        return (String) SpringContextUtils.getHttpServletRequest().getAttribute(MENU_DATA_AUTHOR_RULE_SQL);
    }

    /**
     * 往链接请求里面，传入数据查询条件
     *
     * @param request
     * @param sql
     */
    public static synchronized void installDataSearchConditon(HttpServletRequest request, String sql) {
        String ruleSql = (String)loadDataSearchConditonSQLString();
        if (!StringUtils.hasText(ruleSql)) {
            request.setAttribute(MENU_DATA_AUTHOR_RULE_SQL, sql);
        }
    }

    public static synchronized void installUserInfo(HttpServletRequest request, UserVo userinfo) {
        request.setAttribute(SYS_USER_INFO, userinfo);
    }

    public static synchronized UserVo loadUserInfo() {
        return (UserVo) SpringContextUtils.getHttpServletRequest().getAttribute(SYS_USER_INFO);
    }
}
