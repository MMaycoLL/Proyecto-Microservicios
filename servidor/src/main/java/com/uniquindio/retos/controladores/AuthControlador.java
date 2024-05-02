package com.uniquindio.retos.controladores;

import com.uniquindio.retos.dto.LogDTO;
import com.uniquindio.retos.dto.MensajeDTO;
import com.uniquindio.retos.dto.SesionDTO;
import com.uniquindio.retos.rabbit.publisher.RabbitJsonProducer;
import com.uniquindio.retos.servicios.interfaces.SesionServicio;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/auth")


public class AuthControlador {

    private final SesionServicio sesionServicio;

    public AuthControlador(SesionServicio sesionServicio, RabbitJsonProducer producerJson) {
        this.sesionServicio = sesionServicio;
        this.producerJson = producerJson;
    }

    private final RabbitJsonProducer producerJson;

    @Operation(summary = "Iniciar sesión",
            description = "Se inicia sesión con la información especificada en el DTO, en este caso correo electrónico y contraseña.")
    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO sesionDTO) {

        LogDTO logDTO = new LogDTO("Tu aplicación", "LOGIN", "AuthControlador", LocalDateTime.now(), "Resumen del mensaje", "Se ha iniciado sesion correctamente");
        producerJson.sendJsonMessage(logDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false,
                "Inicio de sesión exitoso"));

    }
}