package com.api.mktplace.nyk_marketplace.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    public static final String QUEUE_1 = "async_queue";
    public static final String QUEUE_2 = "sync_queue";
    public static final String EXCHANGE = "marketplace_exchange";
    public static final String ROUTING_KEY_1 = "async_routingkey";
    public static final String ROUTING_KEY_2 = "sync_routingkey";

    @Bean
    public Queue async_queue(){
        return new Queue(QUEUE_1);
    }

    @Bean
    public Queue sync_queue(){
        return new Queue(QUEUE_2);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding firstBinding(Queue async_queue, TopicExchange exchange){
        return BindingBuilder.bind(async_queue).to(exchange).with(ROUTING_KEY_1);
    }

    @Bean
    public Binding secondBinding(Queue sync_queue, TopicExchange exchange){
        return BindingBuilder.bind(sync_queue).to(exchange).with(ROUTING_KEY_2);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
