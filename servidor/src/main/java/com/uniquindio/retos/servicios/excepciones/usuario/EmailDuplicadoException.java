package com.uniquindio.retos.servicios.excepciones.usuario;

public class EmailDuplicadoException extends Exception {
    public EmailDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
