package com.uniquindio.reto_1.dto

import java.time.LocalDateTime

data class LogDTO(

    var application: String = "",
    var logType: String = "",
    var module: String = "",
    var dateTime: LocalDateTime = LocalDateTime.now(),
    var summary: String = "",
    var description: String = ""
){}
