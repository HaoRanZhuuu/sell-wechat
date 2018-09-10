package com.zhuhaoran.sell.service;

import com.zhuhaoran.sell.po.SellerInfo;
import org.springframework.stereotype.Service;

public interface SellerService {

    SellerInfo findSellerInfoByUsername(String username);
}
