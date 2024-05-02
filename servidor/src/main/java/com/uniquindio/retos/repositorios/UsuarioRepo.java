package com.uniquindio.retos.repositorios;

import com.uniquindio.retos.entidades.Usuario;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.email = :email")
    Usuario buscarUsuarioPorEmail(String email);

    @Query("select u from Usuario u where u.cedula = :cedula")
    Optional<Usuario> buscarUsuarioPorCedula(String cedula);

    Usuario findByEmail(String email);
}
