package com.uniquindio.retos.servicios.interfaces;

import com.uniquindio.retos.dto.SesionDTO;
import com.uniquindio.retos.dto.TokenDTO;

public interface SesionServicio {

    TokenDTO login(SesionDTO sesionDTO);


    void logout(int idUsuario);


    boolean validarCredenciales(SesionDTO sesionDTO);
}
