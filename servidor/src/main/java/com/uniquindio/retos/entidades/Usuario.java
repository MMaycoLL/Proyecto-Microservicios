package com.uniquindio.retos.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {


    @Column(length = 20, nullable = false)
    private String telefono;

    @Column(length = 100, nullable = false)
    private String direccion;

}
