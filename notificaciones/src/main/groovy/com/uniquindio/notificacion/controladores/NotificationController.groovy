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
@EnableScheduling
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private final EmailServicio emailServicio;

    public NotificationController(EmailServicio emailServicio) {
        this.emailServicio = emailServicio;
    }

    @Operation(summary = "Endpoint de notificaciones",
            description = "Envía un email a un destinatario, se implementará para notificar cambios en los microservicios")
    @PostMapping("")
    public ResponseEntity<String> enviarEmail() {
        // Datos quemados
        String asunto = "Estado de los Microservicios";
        String cuerpo = "Uno o más microservicios están caídos.";
        String email = "mmmaycolll@hotmail.com";

        // Crear un EmailDTO con los datos quemados
        EmailDTO emailDTO = new EmailDTO(asunto, cuerpo, email);

        try {
            emailServicio.enviarEmail(emailDTO);
            return ResponseEntity.ok("Email enviado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error enviando el email: " + e.getMessage());
        }
    }
}
