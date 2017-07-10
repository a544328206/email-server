package com.basic.rabbitmq.productor.listener;

import com.basic.rabbitmq.productor.model.SendMessage;
import com.rabbitmq.client.ConfirmListener;
import org.springframework.stereotype.Service;

import static java.util.Collections.synchronizedSortedMap;

import java.io.IOException;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 消息确认机制，ConfirmListener是一个接口，实现这个接口，自己来确认就行
 *
 * Created by sdc on 2017/7/9.
 */
//@Service("mqConfirmListener")
public class MqConfirmListener implements ConfirmListener {

    /**
     * 失败的消息队列，不过，目前还没做这个
     */
    private final SortedMap<Long, String> unConfirmedMessageDeliveryTags = synchronizedSortedMap(new TreeMap<Long, String>());

    /**
     * 重新发送消息
     */
   private Queue<SendMessage> resendMessages = new ConcurrentLinkedQueue<SendMessage>();

    @Override
    public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        if (multiple) {
            System.out.println("ACK:监听器发行消息失败了");
            unConfirmedMessageDeliveryTags.headMap(deliveryTag + 1).clear();
        } else {
            System.out.println("ACK:监听器监听消息成功了");
            unConfirmedMessageDeliveryTags.remove(deliveryTag);
        }
    }

    @Override
    public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        if (multiple) {
            System.out.println("NACK:处理消息1");
        } else {
            System.out.println("NACK:处理消息2");
        }
        //获取到消息
        String message = unConfirmedMessageDeliveryTags.get(deliveryTag);
        if (message != null) {
            resendMessages.add(new SendMessage(message));
            // Messages in resendMessages will be sent later in its own
            // thread
        }
    }
}
