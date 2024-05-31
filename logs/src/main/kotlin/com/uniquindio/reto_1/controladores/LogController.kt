package com.uniquindio.reto_1.controladores

import com.uniquindio.reto_1.entidades.Log
import com.uniquindio.reto_1.servicios.LogService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController("/api/logs")
class LogController(private val logService: LogService) {

    @Operation(summary = "Obtener los logs con opciones de paginación y filtrado por fecha y tipo de log")
    @GetMapping("")
    fun getLogs(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
        @RequestParam(required = false) startDate: LocalDateTime?,
        @RequestParam(required = false) endDate: LocalDateTime?,
        @RequestParam(required = false) logType: String?
    ): Page<Log> {
        val pageable = PageRequest.of(page, size, Sort.by("dateTime").descending())
        return logService.getLogs(pageable, startDate, endDate, logType)
    }
}




