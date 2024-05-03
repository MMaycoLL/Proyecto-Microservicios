package com.uniquindio.retos.controladores;

import com.uniquindio.retos.dto.MensajeDTO;
import com.uniquindio.retos.dto.UsuarioDTO;
import com.uniquindio.retos.dto.UsuarioGetDTO;
import com.uniquindio.retos.entidades.Usuario;
import com.uniquindio.retos.repositorios.ModeradorRepo;
import com.uniquindio.retos.repositorios.UsuarioRepo;
import com.uniquindio.retos.servicios.interfaces.ModeradorServicio;
import com.uniquindio.retos.servicios.interfaces.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uniquindio.retos.dto.LogDTO;
import com.uniquindio.retos.rabbit.publisher.RabbitJsonProducer;
import java.time.LocalDateTime;


import java.util.List;

@RestController
@RequestMapping("/api/usuario")

public class UsuarioControlador {

    public UsuarioControlador(UsuarioServicio usuarioServicio, UsuarioRepo usuarioRepo, RabbitJsonProducer producerJson) {
        this.usuarioServicio = usuarioServicio;
        this.usuarioRepo = usuarioRepo;
        this.producerJson = producerJson;

    }

    private final UsuarioServicio usuarioServicio;
    private final UsuarioRepo usuarioRepo;
    private final RabbitJsonProducer producerJson;

    @Operation(summary = "Crear un nuevo usuario",
            description = "Se crea un usuario con la información especificada en el DTO.")
    @PostMapping("")
    public ResponseEntity<MensajeDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {
        LogDTO logDTO = new LogDTO("API", "New user", "AuthControlador", LocalDateTime.now(), "Resumen del mensaje", "Se  ha creado un nuevo usuario");
        producerJson.sendJsonMessage(logDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MensajeDTO(
                        HttpStatus.CREATED,
                        false,
                        usuarioServicio.crearUsuario(usuarioDTO)));
    }

    @Operation(summary = "Actualizar un usuario",
            description = "Se actualiza la información del usuario correspondiente al código o Id de usuario especificado.")
    @PutMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO> actualizarUsuario(@PathVariable int idUsuario,  @Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {

        LogDTO logDTO = new LogDTO("API", "UPDATE", "AuthControlador", LocalDateTime.now(), "Resumen del mensaje", "Se  ha modificado un usuario");
        producerJson.sendJsonMessage(logDTO);

        return ResponseEntity.status(HttpStatus.OK).body(
                new MensajeDTO(HttpStatus.OK,
                        false,
                        usuarioServicio.actualizarUsuario(idUsuario, usuarioDTO)));
    }

    @Operation(summary = "Eliminar un usuario",
            description = "Se elimina la información del usuario correspondiente al código o Id de usuario especificado.")
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO> eliminarUsuario(@PathVariable int idUsuario) throws Exception {

        LogDTO logDTO = new LogDTO("API", "Users delete", "AuthControlador", LocalDateTime.now(), "Resumen del mensaje", "Se  ha eliminado el usuario");
        producerJson.sendJsonMessage(logDTO);

        return ResponseEntity.status(HttpStatus.OK).body(
                new MensajeDTO(
                        HttpStatus.OK,
                        false,
                        usuarioServicio.eliminarUsuario(idUsuario)));
    }

    @Operation(summary = "Obtener un usuario",
            description = "Obtiene la información del usuario correspondiente al código o Id de usuario especificado.")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<MensajeDTO> obtenerUsuario(@PathVariable int idUsuario) throws Exception {

        LogDTO logDTO = new LogDTO("API", "Users", "AuthControlador", LocalDateTime.now(), "Resumen del mensaje", "Se  ha consultado un usuario");
        producerJson.sendJsonMessage(logDTO);

        return ResponseEntity.status(HttpStatus.OK).body(
                new MensajeDTO(HttpStatus.OK,
                        false,
                        usuarioServicio.obtenerUsuario(idUsuario)));
    }


    @GetMapping("")
    public Page<Usuario> getUsuario(Pageable pageable) {

        return usuarioRepo.findAll(pageable);
    }
}
