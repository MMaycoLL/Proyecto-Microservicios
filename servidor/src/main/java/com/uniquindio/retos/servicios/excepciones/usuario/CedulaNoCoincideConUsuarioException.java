package com.uniquindio.retos.servicios.excepciones.usuario;

public class CedulaNoCoincideConUsuarioException extends Exception {

    public CedulaNoCoincideConUsuarioException(String mensaje) {
        super(mensaje);
    }
}
