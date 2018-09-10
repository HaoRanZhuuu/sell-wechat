package com.zhuhaoran.sell.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhuhaoran.sell.enums.ProductStatusEnum;
import com.zhuhaoran.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Component
@Entity
@Data
@DynamicUpdate
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
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    //类目编号
    private Integer categoryType;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
