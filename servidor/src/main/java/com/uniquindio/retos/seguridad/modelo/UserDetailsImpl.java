package com.uniquindio.retos.seguridad.modelo;

import com.uniquindio.retos.entidades.Moderador;
import com.uniquindio.retos.entidades.Persona;
import com.uniquindio.retos.entidades.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private String username, password;
    private int codigo;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Persona user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user instanceof Usuario) {
            authorities.add(new SimpleGrantedAuthority("CLIENTE"));
        } else if (user instanceof Moderador) {
            authorities.add(new SimpleGrantedAuthority("MODERADOR"));
        }
        return new UserDetailsImpl(user.getEmail(), user.getContrasenia(), user.getIdPersona(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getCodigo() {
        return codigo;
    }
}
