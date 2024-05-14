package com.uniquindio.health_check.dto

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@Canonical
@TypeChecked
class Microservicio {
    String nombre
    String endpoint
    int frecuenciaMonitoreo // en segundos
    String emailsNotificaciones
}