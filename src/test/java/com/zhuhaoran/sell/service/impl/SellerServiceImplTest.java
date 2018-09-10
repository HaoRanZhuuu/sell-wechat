package com.zhuhaoran.sell.service.impl;

import com.zhuhaoran.sell.po.SellerInfo;
import com.zhuhaoran.sell.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerService sellerService;
    @Test
    public void findSellerInfoByUsername() {
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername("admin");
        Assert.assertEquals("1534643126125942434",sellerInfo.getSellerId());
    }
}