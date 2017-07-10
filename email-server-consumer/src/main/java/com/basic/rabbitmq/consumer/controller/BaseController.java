package com.basic.rabbitmq.controller;

import com.basic.rabbitmq.constant.WebStatusEnum;
import com.basic.rabbitmq.model.ResponseVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by sdc on 2017/7/6.
 */
public class BaseController {
    /**
     * 生成统一的返回响应对象
     *
     * @param webStatusEnum 状态码枚举
     * @param data 数据对象
     * @param <T> 数据对象类型参数
     * @return
     */
    public <T> ResponseVo generateResponseVo(WebStatusEnum webStatusEnum, T data) {
        return new ResponseVo(webStatusEnum.getCode(), webStatusEnum.getDesc(), data);
    }

    /**
     * 获取当前会话
     *
     * @param request 请求
     * @return httpSession
     */
    public HttpSession getCurrentSession(HttpServletRequest request) {
        return request.getSession();
    }


}
