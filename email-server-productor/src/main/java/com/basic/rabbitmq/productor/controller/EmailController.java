package com.basic.rabbitmq.productor.controller;

import com.basic.rabbitmq.productor.constant.WebStatusEnum;
import com.basic.rabbitmq.productor.model.ResponseVo;
import com.basic.rabbitmq.productor.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
