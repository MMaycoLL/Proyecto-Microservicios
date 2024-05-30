package com.uniquindio.notificacion.servicios.implementacion


import com.uniquindio.notificacion.dto.EmailDTO
import com.uniquindio.notificacion.servicios.interfaces.EmailServicio
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailServicioImpl implements EmailServicio {
    private final JavaMailSender javaMailSender

    EmailServicioImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender
    }

    @Override
    void enviarEmail(EmailDTO emailDTO) throws Exception {
        MimeMessage mensaje = javaMailSender.createMimeMessage()
        MimeMessageHelper helper = new MimeMessageHelper(mensaje)
        try {
            helper.setSubject(emailDTO.asunto)
            helper.setText(emailDTO.cuerpo, true)
            helper.setTo(emailDTO.destinatario)
            helper.setFrom('no_reply@dominio.com')
            javaMailSender.send(mensaje)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
