package com.zhuhaoran.sell.dao;

import com.zhuhaoran.sell.po.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;
    @Autowired
    private ProductCategory productCategory;
    @Test
    public void findOneTest(){
        productCategory = repository.findById(1).get();
        log.info(productCategory.toString());
    }
    @Test
    public void saveTest(){
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(4);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest(){
        productCategory = repository.findById(2).get();
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> lists = repository.findByCategoryTypeIn(list);
        log.info(lists.toString());
        Assert.assertNotEquals(0,lists.size());
    }
}