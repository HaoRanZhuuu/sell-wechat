package com.zhuhaoran.sell.handler;

import com.zhuhaoran.sell.exception.SellException;
import com.zhuhaoran.sell.exception.SellerAuthorizeException;
import com.zhuhaoran.sell.utils.ResultVoUtil;
import com.zhuhaoran.sell.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:/seller/index");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handlerSellerException(SellException e){

        return ResultVoUtil.error(e.getCode(),e.getMessage());
    }
}
