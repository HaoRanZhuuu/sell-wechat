package com.zhuhaoran.sell.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;
    //商品名字
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品小图连接
    private String productIcon;
    //类目编号
    private Integer categoryType;

}
