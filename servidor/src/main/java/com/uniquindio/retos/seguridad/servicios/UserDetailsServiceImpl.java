package com.uniquindio.retos.seguridad.servicios;

import com.uniquindio.retos.entidades.Moderador;
import com.uniquindio.retos.entidades.Usuario;
import com.uniquindio.retos.repositorios.ModeradorRepo;
import com.uniquindio.retos.repositorios.UsuarioRepo;
import com.uniquindio.retos.seguridad.modelo.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepo clienteRepo;
    @Autowired
    private ModeradorRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = clienteRepo.buscarUsuarioPorEmail(email);
        if (usuario == null) {
            Moderador moderador = adminRepo.buscarModeradorPorEmail(email);

            if (moderador == null) throw new UsernameNotFoundException("El usuario no existe");
            return UserDetailsImpl.build(moderador);
        } else {
            return UserDetailsImpl.build(usuario);
        }
    }
}
