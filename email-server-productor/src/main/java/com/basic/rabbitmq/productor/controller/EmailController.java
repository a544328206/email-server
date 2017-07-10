package com.basic.rabbitmq.productor.controller;

import com.basic.rabbitmq.productor.constant.WebStatusEnum;
import com.basic.rabbitmq.productor.service.EmailService;
import com.basic.rabbitmq.productor.model.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 接口测试类
 *
 * Created by sdc on 2017/7/5.
 */
@RestController
@RequestMapping(value = "/email")
public class EmailController extends BaseController{

    @Resource(name = "emailService")
    private EmailService emailService;

    /**
     * 发供邮件服务
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/sendEmail", method = RequestMethod.GET)
    public ResponseVo<?> sendEmail() throws Exception {
        String emailMessage = "邮件消息";
        emailService.sendEmailForQueue(emailMessage);
        return generateResponseVo(WebStatusEnum.SUCCESS, "");
    }

}
