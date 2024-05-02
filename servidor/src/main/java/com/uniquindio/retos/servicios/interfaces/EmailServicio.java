package com.uniquindio.retos.servicios.interfaces;

import com.uniquindio.retos.dto.EmailDTO;

public interface EmailServicio {

    void enviarEmail(EmailDTO emailDTO) throws Exception;
}
