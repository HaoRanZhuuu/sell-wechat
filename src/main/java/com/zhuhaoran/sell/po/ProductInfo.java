package com.zhuhaoran.sell.po;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Component
@Entity
@Data
public class ProductInfo {

    @Id
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
    //商品状态信息0正常1下架
    private Integer productStatus;
    //类目编号
    private Integer categoryType;

}
