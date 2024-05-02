package com.uniquindio.reto_1.entidades

import jakarta.persistence.*

import java.time.LocalDateTime;

@Entity (name = "logs")
data class Log(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idLog: Int = 0,

    @Column(length = 50, nullable = false)
    val application: String = "",

    @Column(length = 50, nullable = false)
    val logType: String = "",

    @Column(length = 50, nullable = false)
    val module: String = "",

    @Column(nullable = false)
    var dateTime: LocalDateTime =  LocalDateTime.now(),

    @Column(length = 50, nullable = false)
    var summary: String = "",

    @Column(length = 50, nullable = false)
    var description: String = ""
)
{
}

