package com.uniquindio.reto_1.rabbit.consumer
/*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitConsumer {

    @Value("\${rabbitmq.queue.name}")
    private lateinit var queueName: String

    @RabbitListener(queues = ["\${rabbitmq.queue.name}"])
    fun consume(message: String?) {
        LOGGER.info(String.format("Mensaje recibido -> %s", message))
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(RabbitConsumer::class.java)
    }
}
*/