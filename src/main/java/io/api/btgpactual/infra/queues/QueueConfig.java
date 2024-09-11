package io.api.btgpactual.infra.queues;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueueConfig {
    public final static String ORDER_CREATED_QUEUE = "order-queue";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue orderCreatedQueue(){
        return new Queue(ORDER_CREATED_QUEUE, true);
    }
}
