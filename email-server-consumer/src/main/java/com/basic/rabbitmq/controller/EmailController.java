package com.basic.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.rabbitmq.constant.WebStatusEnum;
import com.basic.rabbitmq.model.ResponseVo;
import com.basic.rabbitmq.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 邮件测试的类，
 * Created by sdc on 2017/7/5.
 */
@Controller
//@RequestMapping(value = "/email")
public class EmailController extends BaseController{

    @Resource(name = "emailService")
    private EmailService emailService;

    @RequestMapping(value="/registration1", method = RequestMethod.GET)
    public ResponseVo<?> sendEmail() throws Exception {
        return generateResponseVo(WebStatusEnum.SUCCESS, null);
    }

}
