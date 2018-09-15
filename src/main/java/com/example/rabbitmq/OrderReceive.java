package com.example.rabbitmq;

/**
 * Created by Administrator on 2018/9/13 0013.
 */

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderReceive {

    @RabbitListener(bindings= @QueueBinding(
            value= @Queue(value="order-queue", durable="true"),
            exchange= @Exchange(name="order-exchangge", durable="true", type="topic"),
            key= "order.#"
        )
    )

    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws Exception {

        System.out.println("Order Id:"+ "order.getId()");
        Long deliveryTag= (Long)headers.get(AmqpHeaders.DELIVERY_TAG);

        channel.basicAck(deliveryTag, false);
    }
}
