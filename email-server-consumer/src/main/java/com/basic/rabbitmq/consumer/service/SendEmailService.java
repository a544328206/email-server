package com.basic.rabbitmq.consumer.service;

/**
 * 发行邮件服务
 * Created by sdc on 2017/7/5.
 */
public interface HandleEmailService {

    /**
     * 发送邮件任务存入消息队列
     * @param message
     * @throws Exception
     */
    public void sendMessageTo(String message) throws Exception;

}
