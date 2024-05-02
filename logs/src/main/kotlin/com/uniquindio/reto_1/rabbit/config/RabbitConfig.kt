package com.uniquindio.reto_1.rabbit.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfig {


    @Value("\${rabbitmq.queue.name}")
    private val queue: String? = null

    @Value("\${rabbitmq.queue.json.name}")
    private val jsonQueue: String? = null

    @Value("\${rabbitmq.exchange.name}")
    private val exchange: String? = null

    @Value("\${rabbitmq.routing.key}")
    private val routingKey: String? = null

    @Value("\${rabbitmq.routing.json.key}")
    private val routingJsonKey: String? = null


    @Bean
    fun queue(): Queue {
        return Queue(queue)
    }

    @Bean
    fun jsonQueve(): Queue {
        return Queue(jsonQueue)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(exchange)
    }

    @Bean
    fun binding(): Binding {
        return BindingBuilder
            .bind(queue())
            .to(exchange())
            .with(routingKey)
    }

    @Bean
    fun jsonBiding(): Binding {
        return BindingBuilder
            .bind(jsonQueve())
            .to(exchange())
            .with(routingJsonKey)
    }

    @Bean
    fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    fun amqpTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = converter()
        return rabbitTemplate
    }
}