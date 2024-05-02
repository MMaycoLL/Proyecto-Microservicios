package com.uniquindio.retos.rabbit.consumer;


/*
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniquindio.retos.dto.LogDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RabbitJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitJsonConsumer.class);
    private final Queue queue;
    private final ObjectMapper objectMapper;

    public RabbitJsonConsumer(@Qualifier("queue") Queue queue, ObjectMapper objectMapper) {
        this.queue = queue;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumerJsonMessage(LogDTO logDTO) {
        try {
            String logJson = objectMapper.writeValueAsString(logDTO);
            LOGGER.info(String.format("Log Generado -> %s", logJson));
        } catch (JsonProcessingException e) {
            LOGGER.error("Error al procesar el Json", e);
        }
    }
}
*/
