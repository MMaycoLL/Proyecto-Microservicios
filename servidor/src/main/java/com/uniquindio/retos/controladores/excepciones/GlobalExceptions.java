package com.uniquindio.retos.controladores.excepciones;

import com.uniquindio.retos.dto.MensajeDTO;
import com.uniquindio.retos.servicios.excepciones.moderador.ModeradorNoEncontradoException;
import com.uniquindio.retos.servicios.excepciones.usuario.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MensajeDTO> badCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                MensajeDTO(HttpStatus.BAD_REQUEST, true, "Datos de autenticación incorrectos"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO> generalException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
                MensajeDTO(HttpStatus.INTERNAL_SERVER_ERROR, true, e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MensajeDTO> accessDeniedException(AccessDeniedException
                                                                    accessDeniedException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new
                MensajeDTO(HttpStatus.FORBIDDEN, true, "No se puede acceder a este recurso"));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO> validationException(MethodArgumentNotValidException ex) {
        List<String> messages = new ArrayList<>();
        BindingResult results = ex.getBindingResult();
        for (FieldError e : results.getFieldErrors()) {
            messages.add(e.getField() + ": " + e.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new MensajeDTO(HttpStatus.BAD_REQUEST, true,
                messages.toString()));
    }


    @ExceptionHandler(ModeradorNoEncontradoException.class)
    public ResponseEntity<MensajeDTO> throwModeradorNoEncontradoException(ModeradorNoEncontradoException e) {
        return ResponseEntity.badRequest().body(new MensajeDTO(HttpStatus.BAD_REQUEST, true,
                e.getClass()));
    }

    @ExceptionHandler(CedulaNoCoincideConUsuarioException.class)
    public ResponseEntity<MensajeDTO> throwCedulaNoCoincideConUsuarioException(CedulaNoCoincideConUsuarioException e) {
        return ResponseEntity.badRequest().body(new MensajeDTO(HttpStatus.BAD_REQUEST, true,
                e.getClass()));
    }

    @ExceptionHandler(CedulaDuplicadaException.class)
    public ResponseEntity<MensajeDTO> throwCedulaDuplicadaException(CedulaDuplicadaException e) {
        return ResponseEntity.badRequest().body(new MensajeDTO(HttpStatus.BAD_REQUEST, true,
                e.getClass()));
    }

    @ExceptionHandler(CodigoInexistenteException.class)
    public ResponseEntity<MensajeDTO> throwCodigoInexistenteException(CodigoInexistenteException e) {
        return ResponseEntity.badRequest().body(new MensajeDTO(HttpStatus.BAD_REQUEST, true,
                e.getClass()));
    }

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<MensajeDTO> throwEmailDuplicadoException(EmailDuplicadoException e) {
        return ResponseEntity.badRequest().body(new MensajeDTO(HttpStatus.BAD_REQUEST, true,
                e.getMessage()));
    }

    @ExceptionHandler(ContraseniaUsuarioNoCoincideException.class)
    public ResponseEntity<MensajeDTO> throwContraseniaUsuarioNoCoincideException(ContraseniaUsuarioNoCoincideException e) {
        return ResponseEntity.badRequest().body(new MensajeDTO(HttpStatus.BAD_REQUEST, true,
                e.getClass()));
    }


}