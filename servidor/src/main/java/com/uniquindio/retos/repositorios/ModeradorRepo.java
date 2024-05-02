package com.uniquindio.retos.repositorios;

import com.uniquindio.retos.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, Integer> {

    @Query("select u from Moderador u where u.email = :email")
    Moderador buscarModeradorPorEmail(String email);
}
