package com.uniquindio.reto_1.servicios

import com.uniquindio.reto_1.entidades.Log
import com.uniquindio.reto_1.repositorios.LogRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LogService(private val logRepo: LogRepo) {

    fun getLogs(pageable: PageRequest, startDate: LocalDateTime?, endDate: LocalDateTime?, logType: String?): Page<Log> {
        return if (startDate != null && endDate != null && logType != null) {
            logRepo.findByDateTimeBetweenAndLogType(startDate, endDate, logType, pageable)
        } else if (startDate != null && endDate != null) {
            logRepo.findByDateTimeBetween(startDate, endDate, pageable)
        } else if (logType != null) {
            logRepo.findByLogType(logType, pageable)
        } else {
            logRepo.findAll(pageable)
        }
    }
}
