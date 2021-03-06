package com.exx.dzj.entity.user;

import lombok.Data;
import lombok.ToString;
import org.omg.PortableServer.ServantRetentionPolicy;

import java.io.Serializable;
import java.util.List;

/**
 * @Author
 * @Date 2019/3/20 0020 11:24
 * @Description 用于前端使用或渲染
 */
@Data
@ToString
public class UserVo extends UserInfo implements Serializable {

    /**
     * 角色
     */
    private List<String> roles;

    private List<String> roleNames;

    private String deptName;

    private String userToken;

    private String genderName;
}
