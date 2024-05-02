package com.uniquindio.reto_1.rabbit.consumer

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.uniquindio.reto_1.dto.LogDTO
import com.uniquindio.reto_1.entidades.Log
import com.uniquindio.reto_1.repositorios.LogRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class RabbitJsonConsumer(
    @param:Qualifier("queue") private val queue: Queue,
    private val objectMapper: ObjectMapper,
    private val logRepository: LogRepo
) {

    private val LOGGER: Logger = LoggerFactory.getLogger(RabbitJsonConsumer::class.java)

    @RabbitListener(queues = ["\${rabbitmq.queue.json.name}"])
    fun consumeJsonMessage(logDTO: LogDTO) {
        try {
            val log = Log(
                application = logDTO.application,
                logType = logDTO.logType,
                module = logDTO.module,
                dateTime = logDTO.dateTime,
                summary = logDTO.summary,
                description = logDTO.description
            )
            logRepository.save(log)
            LOGGER.info("Log Guardado en la base de datos: $log")
        } catch (e: JsonProcessingException) {
            LOGGER.error("Error al procesar el Json", e)
        }
    }
}




