package com.basic.rabbitmq.productor.service;

/**
 * 邮件服务
 * Created by sdc on 2017/7/5.
 */
public interface EmailService {

    /**
     * 发送邮件任务存入消息队列
     * @param message
     * @throws Exception
     */
    public void sendEmailForQueue(String message) throws Exception;

}
