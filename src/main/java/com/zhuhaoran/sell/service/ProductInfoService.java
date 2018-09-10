package com.zhuhaoran.sell.service;

import com.zhuhaoran.sell.dto.CartDTO;
import com.zhuhaoran.sell.po.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findById(String productId);
    //查找在售的列表
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //增加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减少库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //商品上架
    ProductInfo onSale(String productId);

    //商品下架
    ProductInfo offSale(String productId);
}
