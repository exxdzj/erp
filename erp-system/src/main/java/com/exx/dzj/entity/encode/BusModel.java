package com.exx.dzj.entity.encode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Date 2019/5/31 0031 11:25
 * @Description  业务模块
 */
@Data
@TableName("tab_bus_model")
public class BusModel implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 业务类型(业务编码)
     */
    private String busType;
    /**
     * 业务名称
     */
    private String busName;
    private Integer isEnable;
    private Date createTime;

}
