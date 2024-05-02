package com.uniquindio.retos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    //El @NotBlank es solo para los string
    @NotBlank(message = "El nombre no puede estar vacío")
    @NotNull(message = "El nombre no puede ser nulo")
    @Length(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombreCompleto;


    @NotBlank(message = "El correo no puede estar vacío")
    @NotNull(message = "El correo no puede ser nulo")
    @Length(max = 100, message = "El correo no puede tener más de 100 caracteres")
    @Email(message = "El correo no tiene un formato válido")
    private String email;

    @NotBlank(message = "La cédula no puede estar vacía")
    @NotNull(message = "El cedula no puuede ser nula")
    @Length(max = 10, message = "La cedula no puede tener más de 10 caracteres")
    private String cedula;

    @NotBlank(message = "La direccion no puede estar vacía")
    @NotNull(message = "La direccion no puede ser nula")
    @Length(max = 100, message = "La direccion no puede tener más de 100 caracteres")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    @NotNull(message = "El telefono no puede ser nulo")
    @Length(max = 20, message = "El telefono no puede tener más de 100 caracteres")
    private String telefono;

    @NotBlank(message = "El nombre no puede estar vacío")
    @NotNull(message = "El nombre no puede ser nulo")
    @Length(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String contrasenia;


}
