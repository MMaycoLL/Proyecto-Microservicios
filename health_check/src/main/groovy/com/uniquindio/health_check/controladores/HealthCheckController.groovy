package com.uniquindio.health_check.controladores

import com.uniquindio.health_check.dto.EmailDTO
import com.uniquindio.health_check.dto.Microservicio
import com.uniquindio.health_check.servicios.interfaces.EmailServicio
import lombok.AllArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@RestController
@AllArgsConstructor
@EnableScheduling
@RequestMapping("/api/health")
class HealthCheckController {

    final RestTemplate restTemplate = new RestTemplate()
    final Map<String, Microservicio> microserviciosRegistrados = new HashMap<>()
    @Autowired
    final EmailServicio emailServicio
    final Logger logger = LoggerFactory.getLogger(HealthCheckController)

    @PostMapping("")
    ResponseEntity<String> registerMicroservicio(@RequestBody Microservicio microservicio) {
        if (microserviciosRegistrados.containsKey(microservicio.nombre)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El microservicio ya está registrado.")
        }
        microserviciosRegistrados.put(microservicio.nombre, microservicio)
        return ResponseEntity.status(HttpStatus.CREATED).body("Microservicio registrado correctamente.")
    }

    @GetMapping("")
    ResponseEntity<Map<String, String>> getHealth() {
        def healthStatus = [:]
        microserviciosRegistrados.each { nombre, microservicio ->
            String url = microservicio.endpoint

            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map)
                String status = response.body?.get("status")
                if (status && status == "UP") {
                    healthStatus.put(nombre, "Saludable")
                } else {
                    healthStatus.put(nombre, "No saludable")
                }
            } catch (Exception e) {
                healthStatus.put(nombre, "No saludable (Error: ${e.message})")
            }
        }
        return ResponseEntity.ok(healthStatus as Map<String, String>)
    }

    @GetMapping("/{microservicio}")
     ResponseEntity<Map<String, String>> getHealthByMicroservicio(@PathVariable String microservicio) {
        if (!microserviciosRegistrados.containsKey(microservicio)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Microservicio no encontrado."));
        }

        Microservicio servicio = microserviciosRegistrados.get(microservicio);
        String url = servicio.getEndpoint();
        String email = servicio.getEmailsNotificaciones();

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            String status = (String) response.getBody().get("status");
            Map<String, String> healthStatus = new HashMap<>();
            healthStatus.put("status", status);
            healthStatus.put("email", email);
            return ResponseEntity.ok(healthStatus);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al obtener el estado del microservicio: " + e.getMessage()));
        }
    }

    private void sendEmailNotification(String microservicio, String email, String message) {
        try {
            emailServicio.enviarEmail(new EmailDTO("Health Check Notification", message, email))
        } catch (Exception e) {
            logger.error("Error sending email notification for microservicio ${microservicio}", e)
        }
    }

    // Tarea programada para controles de salud periódicos
    @Scheduled(fixedDelay = 60000L)
    // cada 5 segundos
    void checkHealth() {
        microserviciosRegistrados.each { nombre, microservicio ->
            String url = microservicio.endpoint
            String email = microservicio.emailsNotificaciones

            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map)
                String status = response.body?.get("status")
                if (status != "UP") {
                    sendEmailNotification(microservicio.nombre, email, "Microservicio ${microservicio.nombre} no está saludable")
                }
            } catch (RestClientException e) {
                // Manejar errores de conexión específicamente
                logger.error("Error connecting to microservicio {} at {}", microservicio.nombre, url, e)
                sendEmailNotification(microservicio.nombre, email, "Error al conectar con microservicio ${microservicio.nombre}")
            } catch (Exception e) {
                logger.error("Error checking health of microservicio {}", microservicio.nombre, e)
            }
        }
    }
}
