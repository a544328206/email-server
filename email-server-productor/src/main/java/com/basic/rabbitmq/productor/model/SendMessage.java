package com.basic.rabbitmq.productor.model;

import java.io.Serializable;

/**
 * 发送消息的封装类
 *
 * Created by sdc on 2017/7/9.
 */
public class SendMessage implements Serializable{

    /**
     * 消息的头部
     */
    private String messageHeader;

    /**
     * 消息的具体内容
     */
    private String messageBody;

    public SendMessage(String messageBody) {
        this.messageBody = messageBody;
    }

    public SendMessage(String messageHeader, String messageBody) {
        this.messageHeader = messageHeader;
        this.messageBody = messageBody;
    }

    public String getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
