package com.basic.rabbitmq.productor.service.impl;

import com.basic.rabbitmq.productor.service.EmailService;
import com.basic.rabbitmq.productor.util.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 邮件服务
 * Created by sdc on 2017/7/5.
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

//    @Resource( name = "rabbitTemplate" )
//    private RabbitTemplate rabbitTemplate;

    @Value("${emial.server.exchange}")
    private String exchange;

    @Value("${emial.server.routekey}")
    private String routeKey;

    @Resource(name = "messageSender")
    private MessageSender messageSender;

    @Override
    public void sendEmailForQueue(String message) throws Exception {
        try {
//            rabbitTemplate.convertAndSend(exchange, routeKey, message);
            messageSender.handlerMessage(message);
        }catch (Exception e){
//            logger.error("EmailServiceImpl.sendEmail", ExceptionUtils.getMessage(e));
            e.printStackTrace();
        }
    }

}
