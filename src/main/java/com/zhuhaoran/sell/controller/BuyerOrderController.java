package com.zhuhaoran.sell.controller;

import com.zhuhaoran.sell.converter.OrderFormToOrderDTOConverter;
import com.zhuhaoran.sell.dto.OrderDTO;
import com.zhuhaoran.sell.enums.ResultEnum;
import com.zhuhaoran.sell.exception.SellException;
import com.zhuhaoran.sell.form.OrderForm;
import com.zhuhaoran.sell.service.BuyerService;
import com.zhuhaoran.sell.service.OrderService;
import com.zhuhaoran.sell.utils.ResultVoUtil;
import com.zhuhaoran.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    @PostMapping("/create")
    //创建订单
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("创建订单，参数不正确， orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("创建订单，购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVoUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list( String openid, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){

        if (StringUtils.isEmpty(openid)){
            log.error("list方法异常，opid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);

        return ResultVoUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(String openid,String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(String openid,String orderId){
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }
}
