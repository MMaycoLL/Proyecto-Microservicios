package com.uniquindio.notificacion.servicios.interfaces

import com.uniquindio.notificacion.dto.EmailDTO

interface EmailServicio {
    void enviarEmail(EmailDTO emailDTO) throws Exception
}