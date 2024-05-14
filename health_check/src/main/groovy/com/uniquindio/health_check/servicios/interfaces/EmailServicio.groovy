package com.uniquindio.health_check.servicios.interfaces

import com.uniquindio.health_check.dto.EmailDTO

interface EmailServicio {
    void enviarEmail(EmailDTO emailDTO) throws Exception
}