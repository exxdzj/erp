package com.exx.dzj.entity.encode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Date 2019/8/7 0007 16:54
 * @Description  销售单编码
 */
@Data
@TableName("tab_sale_encode")
public class SaleEncodeBean implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Date saleDate;
    private Integer nextValue;
}
