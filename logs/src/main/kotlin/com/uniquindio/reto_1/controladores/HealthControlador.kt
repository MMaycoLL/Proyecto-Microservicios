package com.uniquindio.reto_1.controladores

import com.uniquindio.reto_1.health.MyHealthIndicator
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.health.Health
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthControlador {
    /**
     * Inyección de dependencia del indicador de salud personalizado.
     */
    @Autowired
    private val healthIndicator: MyHealthIndicator? = null

    /**
     * Tiempo de inicio del servicio, utilizado para calcular el tiempo al aire.
     */
    private val startTime = System.currentTimeMillis()


    @Operation(
        summary = "Verificar salud del servicio",
        description = "Se verifica el estado de salud del servicio, incluyendo el tiempo al aire, nombre del servicio y versión."
    )
    @GetMapping("/health")
    fun health(): Health {
        val builder = Health.up()
        val uptime = System.currentTimeMillis() - startTime
        val uptimeString = formatUptime(uptime)
        builder.withDetail("uptime", uptimeString)
        builder.withDetail("service", "Gestion_logs")
        builder.withDetail("version", "1.2.0")
        return builder.build()
    }


    @Operation(
        summary = "Verificar tiempo al aire del servicio",
        description = "Se devuelve el tiempo al aire del servicio en formato HH:MM:SS."
    )
    @GetMapping("/health/ready")
    fun ready(): String {
        val uptime = System.currentTimeMillis() - startTime
        return formatUptime(uptime)
    }


    @Operation(
        summary = "Verificar si el servicio está vivo",
        description = "Se devuelve un indicador de que el servicio está vivo y funcionando correctamente."
    )
    @GetMapping("/health/live")
    fun live(): Health {
        return Health.up().build()
    }

    /**
     * Método auxiliar para formatear el tiempo al aire en formato HH:MM:SS.
     */
    private fun formatUptime(uptime: Long): String {
        var seconds = uptime / 1000
        var minutes = seconds / 60
        val hours = minutes / 60
        minutes %= 60
        seconds %= 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}


