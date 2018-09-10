package com.zhuhaoran.sell.service.impl;

import com.zhuhaoran.sell.dao.SellerInfoRepository;
import com.zhuhaoran.sell.po.SellerInfo;
import com.zhuhaoran.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByUsername(String username) {
        return sellerInfoRepository.findByUsername(username);

    }
}
