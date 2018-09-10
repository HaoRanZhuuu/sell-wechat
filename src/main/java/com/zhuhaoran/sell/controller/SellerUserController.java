package com.zhuhaoran.sell.controller;

import com.zhuhaoran.sell.constant.CookieConstant;
import com.zhuhaoran.sell.constant.RedisConstant;
import com.zhuhaoran.sell.enums.ResultEnum;
import com.zhuhaoran.sell.po.SellerInfo;
import com.zhuhaoran.sell.service.SellerService;
import com.zhuhaoran.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("common/index");
    }

    @GetMapping("/login")
    public ModelAndView login(String username, String password , HttpServletResponse response, Map<String, Object> map){

        //数据库校验
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(username);
        if (sellerInfo == null) {
            map.put("message",ResultEnum.LOGIN_USER_NOT_EXIST.getMessage());
            map.put("url","/sell/seller/index");
            return new ModelAndView("common/error",map);
        }
        if (!sellerInfo.getPassword().equals(password) ) {
            map.put("message",ResultEnum.LOGIN_FILE.getMessage());
            map.put("url","/sell/seller/index");
            return new ModelAndView("common/error",map);
        }
        //设置token
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(RedisConstant.TOKEN_PREFIX+token,username,expire,TimeUnit.SECONDS);

        //设置到cookie
        CookieUtil.set(response,CookieConstant.Token,token,expire);
        return new ModelAndView("redirect:/seller/order/list");
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        Cookie cookie = CookieUtil.get(request,CookieConstant.Token);
        if (cookie != null) {
            redisTemplate.opsForValue().getOperations().delete(RedisConstant.TOKEN_PREFIX + cookie.getValue());
            CookieUtil.set(response,CookieConstant.Token,null,0);
        }
        map.put("message",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
