package com.uniquindio.retos.rabbit.publisher;

import com.uniquindio.retos.dto.LogDTO;
import com.uniquindio.retos.dto.UsuarioGetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private UsuarioGetDTO usuarioDTO;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitJsonProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(LogDTO logDTO) { // Actualiza el tipo de parámetro
        LOGGER.info(String.format("Application: %s, Type: %s, Class: %s, Date: %s,  Summary: %s, Description: %s",
                logDTO.getApplication(), logDTO.getLogType(), logDTO.getModule(), logDTO.getDateTime(), logDTO.getSummary(), logDTO.getDescription()));

        // Utiliza logDTO en lugar de usuarioDTO
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, logDTO);
    }


}
