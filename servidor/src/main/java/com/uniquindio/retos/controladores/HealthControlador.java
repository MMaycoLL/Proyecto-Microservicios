package com.uniquindio.retos.controladores;

import com.uniquindio.retos.health.MyHealthIndicator;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Health health() {
        Health.Builder builder = Health.up();
        long uptime = System.currentTimeMillis() - startTime;
        String uptimeString = formatUptime(uptime);
        builder.withDetail("uptime", uptimeString);
        builder.withDetail("service", "Gestion_usuarios");
        builder.withDetail("version", "1.1.0");
        return builder.build();
    }


    @Operation(summary = "Verificar tiempo al aire del servicio",
            description = "Se devuelve el tiempo al aire del servicio en formato HH:MM:SS.")
    @GetMapping("/health/ready")
    public String ready() {
        long uptime = System.currentTimeMillis() - startTime;
        return formatUptime(uptime);
    }


    @Operation(summary = "Verificar si el servicio está vivo",
            description = "Se devuelve un indicador de que el servicio está vivo y funcionando correctamente.")
    @GetMapping("/health/live")
    public Health live() {
        return Health.up().build();
    }

    /**
     * Método auxiliar para formatear el tiempo al aire en formato HH:MM:SS.
     */
    private String formatUptime(long uptime) {
        long seconds = uptime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        minutes %= 60;
        seconds %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}


