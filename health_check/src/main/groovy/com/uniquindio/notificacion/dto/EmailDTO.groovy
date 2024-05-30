package com.uniquindio.notificacion.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TypeChecked

@ToString
@EqualsAndHashCode
@TypeChecked
class EmailDTO {
    String asunto
    String cuerpo
    String destinatario

    EmailDTO() {
        // Default constructor
    }

    EmailDTO(String asunto, String cuerpo, String destinatario) {
        this.asunto = asunto
        this.cuerpo = cuerpo
        this.destinatario = destinatario
    }
}