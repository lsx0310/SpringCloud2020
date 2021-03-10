package com.li.springcloud.common.exception;

import com.li.springcloud.common.response.ResultCode;


/**
 * @ClassName UserException
 * @Description: TODO  自定义用户异常 继承业务异常类 CustomException
 * @Author: li
 * @Date: 2020/3/8 11:58
 * @Version v1.0
 **/
public class UserException extends CustomException{
    public UserException(ResultCode resultCode){
        super(resultCode);
    }
}
