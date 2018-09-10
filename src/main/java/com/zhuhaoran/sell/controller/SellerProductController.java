package com.zhuhaoran.sell.controller;


import com.zhuhaoran.sell.dto.OrderDTO;
import com.zhuhaoran.sell.exception.SellException;
import com.zhuhaoran.sell.form.ProductForm;
import com.zhuhaoran.sell.po.ProductCategory;
import com.zhuhaoran.sell.po.ProductInfo;
import com.zhuhaoran.sell.service.ProductCategoryService;
import com.zhuhaoran.sell.service.ProductInfoService;
import com.zhuhaoran.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1,size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(String productId,Map<String,Object> map){
        try {
            productInfoService.onSale(productId);
        }catch (SellException e){
            map.put("message",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(String productId,Map<String,Object> map){
        try {
            productInfoService.offSale(productId);
        }catch (SellException e){
            map.put("message",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(required = false) String productId,Map<String,Object> map){

        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findById(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有类目

        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList",categoryList);

        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult, Map<String, Object> map) {
        if (bindingResult.hasErrors()){
            map.put("message",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //新增
            if (!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productInfoService.findById(productForm.getProductId());
            } else {
                productForm.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(productForm,productInfo);
            productInfoService.save(productInfo);
        } catch (SellException e) {
            map.put("message",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }
}
