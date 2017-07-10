package com.basic.rabbitmq.productor.util;

import com.basic.rabbitmq.productor.listener.MqConfirmListener;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 消息的发送者，公共类
 * Created by sdc on 2017/7/9.
 */
@Service("messageSender")
public class MessageSender {

    /**
     * 工厂类
     */
    @Resource(name = "connectionFactory")
    private ConnectionFactory factory;

    /**
     * 交换机名字
     */
    @Value("${emial.server.exchange}")
    private String exchangeName;

    /**
     *    队列名字
     */
    @Value("${emial.server.queue}")
    private String queueName;

    /**
     * 路由建
     */
    @Value("${emial.server.routekey}")
    private String routingKey;

    /**
     * 绑定到哪个队列上的
     */
    @Value("${emial.server.exchange.bindingkey}")
    private String bindingKey;

//    public MessageSender(String exchangeName, String queueName, String routingKey, String bindingKey) {
//        this.factory = factory;
//        this.exchangeName = exchangeName;
//        this.queueName = queueName;
//        this.routingKey = routingKey;
//        this.bindingKey = bindingKey;
//    }

    /**
     * 处理消息类
     * @throws Exception
     */
    public void handlerMessage(String message) throws Exception {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//        //创建exchange
        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        //创建队列
        channel.queueDeclare(queueName, true, false, false, null);
        //绑定exchange和queue
        channel.queueBind(queueName, exchangeName, bindingKey);
        //设置confirm模式
        channel.confirmSelect();
        channel.addConfirmListener(new MqConfirmListener());
        //第一个参数是exchangeName(默认情况下代理服务器端是存在一个""名字的exchange的,
        //因此如果不创建exchange的话我们可以直接将该参数设置成"",如果创建了exchange的话
        //我们需要将该参数设置成创建的exchange的名字),第二个参数是路由键
        channel.basicPublish(exchangeName, routingKey , MessageProperties.PERSISTENT_BASIC, message.getBytes());
        if(channel.waitForConfirms())
        {
            System.out.println("发送成功");
        }

    }

}
