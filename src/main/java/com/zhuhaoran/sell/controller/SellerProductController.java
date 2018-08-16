package com.zhuhaoran.sell.controller;


import com.zhuhaoran.sell.dto.OrderDTO;
import com.zhuhaoran.sell.po.ProductInfo;
import com.zhuhaoran.sell.service.ProductInfoService;
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
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    ProductInfoService productInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "2") Integer size, Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1,size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
}
