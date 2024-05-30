package com.uniquindio.notificacion.controladores

import com.uniquindio.notificacion.dto.EmailDTO
import com.uniquindio.notificacion.servicios.interfaces.EmailServicio
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@AllArgsConstructor
@EnableScheduling
@RequestMapping("/api/notification")
class NotificationController {

    @Autowired
    final EmailServicio emailServicio

    @Operation(summary = "Endpoint de notificaciones",
            description = "Envía un email a un destinatario, se implementara para notificar cambios en los microservicios")


    @PostMapping("")
    ResponseEntity<String> enviarEmail(
            @RequestParam(name = "asunto", required = true) @Schema(description = "Asunto del correo") String asunto,
            @RequestParam(name = "cuerpo", required = true) @Schema(description = "Cuerpo del correo") String cuerpo,
            @RequestParam(name = "email", required = true) @Schema(description = "Dirección de correo electrónico del destinatario") String email)
    {


        // Crear un EmailDTO con los datos recibidos
        EmailDTO emailDTO = new EmailDTO(asunto, cuerpo, email)

        try {
            emailServicio.enviarEmail(emailDTO)
            return ResponseEntity.ok("Email enviado exitosamente")
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error enviando el email: ${e.message}" as String)
        }
    }
}
