package com.zhuhaoran.sell.dao;

import com.zhuhaoran.sell.po.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByUsername(String username);
}
