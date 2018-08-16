package com.zhuhaoran.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhuhaoran.sell.enums.OrderStatusEnum;
import com.zhuhaoran.sell.enums.PayStatusEnum;
import com.zhuhaoran.sell.po.OrderDetail;
import com.zhuhaoran.sell.po.OrderMaster;
import com.zhuhaoran.sell.utils.EnumUtil;
import com.zhuhaoran.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    //订单ID
    private String orderId;

    //买家名字
    private String buyerName;

    //买家电话
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信OpenId
    private String buyerOpenid;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态，默认状态0为新订单
    private Integer orderStatus;

    //支付状态,默认状态为0未支付
    private Integer payStatus;

    //订单创建时间
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    //订单修改时间
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
