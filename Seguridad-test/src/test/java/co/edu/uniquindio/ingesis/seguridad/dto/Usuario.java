package co.edu.uniquindio.ingesis.seguridad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Usuario {

    private String cedula;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;

}
