package com.uniquindio.reto_1.controladores


import com.uniquindio.reto_1.health.MyHealthIndicator
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.health.Status
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class HealthController {

    /**
     * Inyección de dependencia del indicador de salud personalizado.
     */
    @Autowired
    private lateinit var healthIndicator: MyHealthIndicator

    /**
     * Tiempo de inicio del servicio, utilizado para calcular el tiempo al aire.
     */
    private val startTime = System.currentTimeMillis()

    @Operation(
        summary = "Verificar salud del servicio",
        description = "Se verifica el estado de salud del servicio, incluyendo el tiempo al aire, nombre del servicio y versión."
    )
    @GetMapping("/health")
    fun health(): HealthResponse {
        val response = HealthResponse()
        response.status = "UP"

        // Uptime check
        val uptimeCheck = HealthCheck()
        uptimeCheck.name = "Uptime check"
        uptimeCheck.status = "UP"
        val uptimeDetails = HealthCheckDetails()
        uptimeDetails.from = Instant.ofEpochMilli(startTime).toString()
        uptimeDetails.status = formatUptime(System.currentTimeMillis() - startTime)
        uptimeDetails.service = "Gestion_usuarios"
        uptimeDetails.version = "1.1.0"
        uptimeCheck.data = uptimeDetails
        response.addCheck(uptimeCheck)

        // Service check
        val serviceCheck = HealthCheck()
        serviceCheck.name = "Service check"
        val serviceDetails = HealthCheckDetails()
        serviceDetails.from = Instant.ofEpochMilli(startTime).toString()
        serviceDetails.status = formatUptime(System.currentTimeMillis() - startTime)

        val health = healthIndicator.health() // Obtener el estado real del servicio
        serviceCheck.status = if (health.status == Status.UP) "ALIVE" else "DOWN"
        serviceDetails.status = health.status.code
        serviceDetails.service = "Gestion_usuarios"
        serviceDetails.version = "1.1.1"
        serviceDetails.from = Instant.ofEpochMilli(startTime).toString()
        serviceDetails.status = formatUptime(System.currentTimeMillis() - startTime)
        serviceCheck.data = serviceDetails
        response.addCheck(serviceCheck)

        return response
    }


    private fun formatUptime(uptime: Long): String {
        var seconds = uptime / 1000
        var minutes = seconds / 60
        val hours = minutes / 60
        minutes %= 60
        seconds %= 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    data class HealthResponse(var status: String = "UP", var checks: MutableList<HealthCheck> = ArrayList()) {
        fun addCheck(check: HealthCheck) {
            checks.add(check)
        }
    }

    data class HealthCheck(var name: String = "", var status: String = "", var data: HealthCheckDetails? = null)

    data class HealthCheckDetails(
        var from: String = "",
        var status: String = "",
        var service: String = "",
        var version: String = ""
    )
}



