package cn.cqut.hf.rabbitmq.template;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RabbitSendAndRec {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 9000)
    public void sendMsg() {
        HashMap messageContent = new HashMap();
        messageContent.put("message", "this is a message from Java RabbitMQ");
        String exchange = "exchange.direct";
        String routingKey = "SpringBoot";
        rabbitTemplate.convertAndSend(exchange, routingKey, messageContent);
        System.out.println("发送消息成功");
    }

//    @Scheduled(fixedRate = 6000)
    @RabbitListener(queues = "SpringBoot")
    public void recvice(Object rec) {
        System.out.println("收到消息:" + rec);
        System.out.println("对象类型:" + rec.getClass().getName());
    }
}
