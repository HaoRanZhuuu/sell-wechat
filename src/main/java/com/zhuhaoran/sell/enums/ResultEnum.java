package com.zhuhaoran.sell.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {
    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数异常"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"库存不正确"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态异常"),

    ORDER_UPDATE_FAIL(15,"订单更新异常"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROR(19,"订单用户权限异常"),

    PRODUCT_STATUS_ERROR(20,"商品状态异常"),

    CATEGORY_NOT_EXIST(21,"类目不存在"),

    LOGIN_USER_NOT_EXIST(22,"用户名不存在"),

    LOGIN_FILE(23,"用户名或密码错误，请重新登录"),

    LOGOUT_SUCCESS(24,"注销成功"),

    ORDER_CANCEL_SUCCESS(101,"订单取消成功"),

    ORDER_FINISH_SUCCESS(102,"订单完结成功"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
