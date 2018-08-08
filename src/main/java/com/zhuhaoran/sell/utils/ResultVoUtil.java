package com.zhuhaoran.sell.utils;

import com.zhuhaoran.sell.vo.ResultVo;

public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }

    public static ResultVo error(Integer code,String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(message);
        return resultVo;
    }
}
