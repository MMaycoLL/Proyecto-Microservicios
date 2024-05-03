package com.uniquindio.reto_1.health

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class MyHealthIndicator : HealthIndicator {
    override fun health(): Health {
        return Health.up().build()
    }
}
