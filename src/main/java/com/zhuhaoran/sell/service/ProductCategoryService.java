package com.zhuhaoran.sell.service;

import com.zhuhaoran.sell.po.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
