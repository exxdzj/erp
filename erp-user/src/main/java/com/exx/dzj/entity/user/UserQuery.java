package com.exx.dzj.entity.user;

import lombok.Data;

/**
 * @Author
 * @Date 2019/4/1 0001 14:20
 * @Description
 */
@Data
public class UserQuery extends UserInfo {
    private int page;

    private int limit;
}
