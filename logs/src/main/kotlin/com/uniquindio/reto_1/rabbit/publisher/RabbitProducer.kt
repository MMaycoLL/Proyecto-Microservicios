package com.uniquindio.reto_1.rabbit.publisher
/*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitProducer(private val rabbitTemplate: RabbitTemplate) {

    @Value("\${rabbitmq.exchange.name}")
    private lateinit var exchange: String

    @Value("\${rabbitmq.routing.key}")
    private lateinit var routingKey: String

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(RabbitProducer::class.java)
    }

    fun sendMessage(message: String) {
        LOGGER.info("Message sent -> $message")
        rabbitTemplate.convertAndSend(exchange, routingKey, message)
    }
}
*/