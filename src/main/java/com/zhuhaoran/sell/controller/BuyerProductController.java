package com.zhuhaoran.sell.controller;


import com.zhuhaoran.sell.po.ProductCategory;
import com.zhuhaoran.sell.po.ProductInfo;
import com.zhuhaoran.sell.service.ProductCategoryService;
import com.zhuhaoran.sell.service.ProductInfoService;
import com.zhuhaoran.sell.utils.ResultVoUtil;
import com.zhuhaoran.sell.vo.ProductInfoVo;
import com.zhuhaoran.sell.vo.ProductVo;
import com.zhuhaoran.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    public ResultVo list(){
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //lambda表达式
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    //将对象拷贝，不用重复编写
                    /*productInfoVo.setProductId(productInfo.getProductId());
                    productInfoVo.setProductName(productInfo.getProductName());
                    productInfoVo.setProductPrice(productInfo.getProductPrice());
                    productInfoVo.setProductDescription(productInfo.getProductDescription());
                    productInfoVo.setProductIcon(productInfo.getProductIcon());*/
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtil.success(productVoList);
    }
}
