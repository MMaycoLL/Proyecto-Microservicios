package com.uniquindio.retos.dto;

import com.uniquindio.retos.entidades.Usuario;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioGetDTO {

    private int idUsuario;

    private String nombreCompleto;

    private String email;

    private String direccion;

    private String telefono;

    private String cedula;

    public UsuarioGetDTO(Usuario usuario) {
    }
}