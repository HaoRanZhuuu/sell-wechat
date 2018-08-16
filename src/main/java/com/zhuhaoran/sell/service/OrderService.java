package com.zhuhaoran.sell.service;

import com.zhuhaoran.sell.dto.OrderDTO;
import com.zhuhaoran.sell.po.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findById(String orderId);

    //个人查询列表订单
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //完成订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单
    OrderDTO paid(OrderDTO orderDTO);

    //商家查询列表订单
    Page<OrderDTO> findList(Pageable pageable);
}
