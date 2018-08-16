package com.zhuhaoran.sell.controller;

import com.zhuhaoran.sell.dto.OrderDTO;
import com.zhuhaoran.sell.enums.ResultEnum;
import com.zhuhaoran.sell.exception.SellException;
import com.zhuhaoran.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;
    /**
     *
     * @param page 页码
     * @param size 每页大小
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "2") Integer size, Map<String,Object> map){
        PageRequest request = PageRequest.of(page - 1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(String orderId,Map<String,Object> map){
        try{
            OrderDTO orderDTO= orderService.findById(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("卖家取消订单查不到日志出现异常");
            map.put("message",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("message",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

    @GetMapping("/detail")
    public ModelAndView detail(String orderId,Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findById(orderId);
        }catch (SellException e){
            log.error("查询订单详情出现异常",e);
            map.put("message",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail");
    }

    @GetMapping("/finish")
    public ModelAndView finish(String orderId,Map<String,Object> map){
        try{
            OrderDTO orderDTO= orderService.findById(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("卖家完结订单查不到日志出现异常");
            map.put("message",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("message",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }
}
