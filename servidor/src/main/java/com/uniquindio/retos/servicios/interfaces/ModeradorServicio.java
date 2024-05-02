package com.uniquindio.retos.servicios.interfaces;

import com.uniquindio.retos.dto.UsuarioGetDTO;
import com.uniquindio.retos.entidades.Moderador;
import com.uniquindio.retos.entidades.Usuario;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ModeradorServicio {

    Moderador obtenerModeradorPorId(int idModerador) throws Exception;


}
