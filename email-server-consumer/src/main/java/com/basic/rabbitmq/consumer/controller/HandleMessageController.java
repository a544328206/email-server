package com.basic.rabbitmq.consumer.controller;

import com.basic.rabbitmq.consumer.constant.WebStatusEnum;
import com.basic.rabbitmq.consumer.model.ResponseVo;
import com.basic.rabbitmq.consumer.service.SendEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 邮件测试的类，
 * Created by sdc on 2017/7/5.
 */
@Controller
@RequestMapping(value = "/email")
public class EmailController extends BaseController{

//    @Resource(name = "emailService")
//    private SendEmailService emailService;

    @RequestMapping(value="/registration1", method = RequestMethod.GET)
    public ResponseVo<?> sendEmail() throws Exception {
        return generateResponseVo(WebStatusEnum.SUCCESS, null);
    }

}
