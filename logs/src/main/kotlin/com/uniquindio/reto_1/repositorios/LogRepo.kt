package com.uniquindio.reto_1.repositorios

import com.uniquindio.reto_1.entidades.Log
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime


@Repository
interface LogRepo : JpaRepository<Log, Int> {
    fun findByDateTimeBetweenAndLogType(startDate: LocalDateTime, endDate: LocalDateTime, logType: String, pageable: PageRequest): Page<Log>
    fun findByDateTimeBetween(startDate: LocalDateTime, endDate: LocalDateTime, pageable: PageRequest): Page<Log>
    fun findByLogType(logType: String, pageable: PageRequest): Page<Log>
}
