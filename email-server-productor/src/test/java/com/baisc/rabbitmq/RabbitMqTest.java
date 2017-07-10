package com.baisc.rabbitmq;

import com.basic.rabbitmq.productor.service.EmailService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by sdc on 2017/7/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RabbitMqTest {

    @Resource(name = "emailService")
    private EmailService emailService;

    @Test
    public void helloword() throws  Exception {
        emailService.sendEmailForQueue("test");
    }


}
