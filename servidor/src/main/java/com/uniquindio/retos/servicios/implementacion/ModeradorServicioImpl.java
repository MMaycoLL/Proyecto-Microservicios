package com.uniquindio.retos.servicios.implementacion;

import com.uniquindio.retos.dto.UsuarioGetDTO;
import com.uniquindio.retos.entidades.Moderador;
import com.uniquindio.retos.entidades.Usuario;
import com.uniquindio.retos.repositorios.UsuarioRepo;
import com.uniquindio.retos.servicios.excepciones.moderador.ModeradorNoEncontradoException;
import com.uniquindio.retos.servicios.interfaces.ModeradorServicio;
import com.uniquindio.retos.repositorios.ModeradorRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;

    @Override
    public Moderador obtenerModeradorPorId(int idModerador) throws Exception {
        Optional<Moderador> moderadorOpt = moderadorRepo.findById(idModerador);
        if (moderadorOpt.isPresent()) {
            return moderadorOpt.get();
        } else {
            throw new ModeradorNoEncontradoException("Moderador no encontrado");
        }
    }

}
