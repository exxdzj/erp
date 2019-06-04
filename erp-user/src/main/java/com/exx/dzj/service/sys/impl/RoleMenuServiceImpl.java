package com.exx.dzj.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exx.dzj.entity.role.RoleMenuBean;
import com.exx.dzj.mapper.role.RoleMenuBeanMapper;
import com.exx.dzj.service.sys.RoleMenuService;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2019/6/3 0003 16:13
 * @Description 角色和权限关联 service
 */
@Component
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuBeanMapper, RoleMenuBean> implements RoleMenuService {
}
