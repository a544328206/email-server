package com.basic.rabbitmq.productor.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.swing.event.ChangeEvent;

/**
 * Rabbitmq配置类
 *
 * 配制一些用户名和密码，还有就是配制队列，交换机，还有路由健
 * Created by sdc on 2017/7/4.
 */
@Configuration
@ComponentScan(basePackages = {"com.basic.rabbitmq.productor"})
@PropertySource(value = {"classpath:application.properties"})
public class RabbitMqConfig {

    @Autowired
    private Environment env;

    /**
     * 构建connectionfactory
     * @return
     * @throws Exception
     */
    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(env.getProperty("spring.rabbitmq.host"));
        connectionFactory.setPort(Integer.valueOf(env.getProperty("spring.rabbitmq.port").trim()));
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername(env.getProperty("spring.rabbitmq.username"));
        connectionFactory.setPassword(env.getProperty("spring.rabbitmq.password"));

//        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    /**
     * CachingConnectionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public CachingConnectionFactory cachingConnectionFactory() throws Exception {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory());
        /** 如果要进行消息回调，则这里必须要设置为true */
        cachingConnectionFactory.setPublisherConfirms(true); // 必须要设置

        return cachingConnectionFactory;
    }

    /**
     * RabbitTemplate，类似于jdbctemplate一样的工具类
     * @return
     * @throws Exception
     */
    @Bean
    public RabbitTemplate rabbitTemplate() throws  Exception {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
//        rabbitTemplate.setChannelTransacted(true);
        //这个设置参数
//        rabbitTemplate.setMandatory(true);

//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback());
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback());
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin() throws  Exception {
        return new RabbitAdmin(cachingConnectionFactory());
    }

    /**
     * 通道处理
     * @return
     * @throws Exception
     */
    @Bean
    public Channel channel() throws Exception {
        //队列名字
        String name = env.getProperty("emial.server.queue").trim();
        // 是否持久化
        boolean durable = StringUtils.isNotBlank(env.getProperty("emial.server.queue.durable").trim())?
                Boolean.valueOf(env.getProperty("emial.server.queue.durable").trim()) : true;

        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = StringUtils.isNotBlank(env.getProperty("emial.server.queue.exclusive").trim())?
                Boolean.valueOf(env.getProperty("emial.server.queue.exclusive").trim()) : false;

        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(env.getProperty("emial.server.queue.autoDelete").trim())?
                Boolean.valueOf(env.getProperty("emial.server.queue.autoDelete").trim()) : false;

        ConnectionFactory connectionFactory = connectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channes = connection.createChannel();
        channes.queueDeclare(name, durable, exclusive, autoDelete, null);
        return channes;
    }

    /**
     * 队列
     * @return
     */
    @Bean
    public Queue queue() throws  Exception{
        //队列名字
        String name = env.getProperty("emial.server.queue").trim();
        // 是否持久化
        boolean durable = StringUtils.isNotBlank(env.getProperty("emial.server.queue.durable").trim())?
                Boolean.valueOf(env.getProperty("emial.server.queue.durable").trim()) : true;

        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = StringUtils.isNotBlank(env.getProperty("emial.server.queue.exclusive").trim())?
                Boolean.valueOf(env.getProperty("emial.server.queue.exclusive").trim()) : false;

        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(env.getProperty("emial.server.queue.autoDelete").trim())?
                Boolean.valueOf(env.getProperty("emial.server.queue.autoDelete").trim()) : false;

        return new Queue(name, durable, exclusive, autoDelete);
    }

    /**
     * 配制交换机,交换机类型为topic
     * @return
     */
    @Bean
    public TopicExchange exchange () {
        //交换机的名字
        String name = env.getProperty("emial.server.exchange");
        // 是否持久化
        boolean durable = StringUtils.isNotBlank(env.getProperty("emial.server.exchange.durable").trim())?
                Boolean.valueOf(env.getProperty("emial.server.exchange.durable").trim()) : true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(env.getProperty("emial.server.exchange.autoDelete").trim())?
                Boolean.valueOf(env.getProperty("emial.server.exchange.autoDelete").trim()) : false;
        //创建交换机
        return new TopicExchange(name, durable, autoDelete);
    }

    /**
     * 绑定，交换机要绑定要队列上，交换机才能把消息放入到相应的队列上。
     * @return
     */
    @Bean
    public Binding binding() throws  Exception{
        String routekey = env.getProperty("emial.server.routekey").trim();
        return BindingBuilder.bind(queue()).to(exchange()).with(routekey);
    }

}
