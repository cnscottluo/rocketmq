package org.apache.rocketmq.example.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class FilterProducer {
    public static void main(String[] args) throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("filter_producer_group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message msg = new Message("FilterTopic",
                    "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 设置一些属性
            msg.putUserProperty("a", String.valueOf(i));
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
