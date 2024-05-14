package com.uniquindio.retos.controladores;

import com.uniquindio.retos.health.MyHealthIndicator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HealthControlador {

    /**
     * Inyección de dependencia del indicador de salud personalizado.
     */
    @Autowired
    private MyHealthIndicator healthIndicator;

    /**
     * Tiempo de inicio del servicio, utilizado para calcular el tiempo al aire.
     */
    private final long startTime = System.currentTimeMillis();

    @Operation(summary = "Verificar salud del servicio",
            description = "Se verifica el estado de salud del servicio, incluyendo el tiempo al aire, nombre del servicio y versión.")
    @GetMapping("/health")
    public HealthResponse health() {
        HealthResponse response = new HealthResponse();
        response.setStatus("UP");

        // Uptime check
        HealthCheck uptimeCheck = new HealthCheck();
        uptimeCheck.setName("Uptime check");
        uptimeCheck.setStatus("UP");
        HealthCheckDetails uptimeDetails = new HealthCheckDetails();
        uptimeDetails.setFrom(Instant.ofEpochMilli(startTime).toString());
        uptimeDetails.setStatus(formatUptime(System.currentTimeMillis() - startTime));
        uptimeDetails.setService("Gestion_usuarios");
        uptimeDetails.setVersion("1.1.0");
        uptimeCheck.setData(uptimeDetails);
        response.addCheck(uptimeCheck);

        // Service check
        HealthCheck serviceCheck = new HealthCheck();
        serviceCheck.setName("Service check");
        HealthCheckDetails serviceDetails = new HealthCheckDetails();
        serviceDetails.setFrom(Instant.ofEpochMilli(startTime).toString());
        serviceDetails.setStatus(formatUptime(System.currentTimeMillis() - startTime));


        Health health = healthIndicator.health(); // Obtener el estado real del servicio
        if (health.getStatus().equals(Status.UP)) {
            serviceCheck.setStatus("Alive");
        } else {
            serviceCheck.setStatus("DOWN");
        }

        serviceDetails.setStatus(health.getStatus().getCode());
        serviceDetails.setService("Gestion_usuarios");
        serviceDetails.setVersion("1.1.1");
        serviceDetails.setFrom(Instant.ofEpochMilli(startTime).toString());
        serviceDetails.setStatus(formatUptime(System.currentTimeMillis() - startTime));
        serviceCheck.setData(serviceDetails);
        response.addCheck(serviceCheck);

        return response;
    }


    private String formatUptime(long uptime) {
        long seconds = uptime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        minutes %= 60;
        seconds %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Setter
    @Getter
    static class HealthResponse {
        private String status;
        private List<HealthCheck> checks;

        public HealthResponse() {
            checks = new ArrayList<>();
        }

        public void addCheck(HealthCheck check) {
            this.checks.add(check);
        }
    }

    @Setter
    @Getter
    static class HealthCheck {
        private String name;
        private String status;
        private HealthCheckDetails data;

    }

    @Setter
    @Getter
    static class HealthCheckDetails {
        private String from;
        private String status;
        private String service;
        private String version;

    }
}
