package com.zhuhaoran.sell.service.impl;

import com.zhuhaoran.sell.enums.ProductStatusEnum;
import com.zhuhaoran.sell.po.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Autowired
    private ProductInfo productInfo;
    @Test
    public void findById() {
        productInfo = productInfoService.findById("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productInfoService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> page = productInfoService.findAll(request);
        log.info(page.getTotalElements()+"");
    }

    @Test
    public void save() {
        productInfo.setProductId("123457");
        productInfo.setProductName("煎饼");
        productInfo.setProductPrice(new BigDecimal(4.5));
        productInfo.setProductStock(200);
        productInfo.setProductDescription("好吃，不贵");
        productInfo.setProductIcon("http://yyy.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void onSale() {
        ProductInfo result = productInfoService.onSale("123456");
        Assert.assertEquals(ProductStatusEnum.UP,result.getProductStatusEnum());
    }

    @Test
    public void offSale() {
        ProductInfo result = productInfoService.offSale("123456");
        Assert.assertEquals(ProductStatusEnum.DOWN,result.getProductStatusEnum());
    }
}