package com.uniquindio.retos.servicios.interfaces;

import com.uniquindio.retos.dto.UsuarioDTO;
import com.uniquindio.retos.dto.UsuarioGetDTO;
import com.uniquindio.retos.entidades.Usuario;

public interface UsuarioServicio {

    int crearUsuario(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioGetDTO actualizarUsuario(int idUsuario, UsuarioDTO usuarioDTO) throws Exception;


    int eliminarUsuario(int idUsuario) throws Exception;

    UsuarioGetDTO obtenerUsuario(int idUsuario) throws Exception;

    Usuario obtener(int idUsuario) throws Exception;

}
