package com.uniquindio.retos.servicios.implementacion;

import com.uniquindio.retos.dto.SesionDTO;
import com.uniquindio.retos.dto.TokenDTO;
import com.uniquindio.retos.entidades.Usuario;
import com.uniquindio.retos.repositorios.UsuarioRepo;
import com.uniquindio.retos.seguridad.modelo.UserDetailsImpl;
import com.uniquindio.retos.seguridad.servicios.JwtService;
import com.uniquindio.retos.servicios.interfaces.SesionServicio;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SesionServicioImpl implements SesionServicio {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepo usuarioRepo;

    @Override
    public TokenDTO login(SesionDTO sesionDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sesionDTO.getEmail(),
                        sesionDTO.getContrasenia())
        );

        UserDetails user = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);
        return new TokenDTO(jwtToken);
    }

    @Override
    public void logout(int idUsuario) {

    }

    @Override
    public boolean validarCredenciales(SesionDTO sesionDTO) {
        // Consultar la base de datos para encontrar el usuario con el correo electrónico proporcionado
        Usuario usuario = usuarioRepo.findByEmail(sesionDTO.getEmail());

        // Verificar si se encontró un usuario y si la contraseña coincide
        if (usuario != null && usuario.getContrasenia().equals(sesionDTO.getContrasenia())) {
            return true; // Credenciales válidas
        } else {
            return false; // Credenciales inválidas
        }
    }

}
