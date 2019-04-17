package com.exx.dzj.entity.dept;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author
 * @Date 2019/4/11 0011 9:24
 * @Description  部门 model
 */
@Data
public class DeptBean implements Serializable {

    private Integer isEnable;
    private List<String> depts;
}
