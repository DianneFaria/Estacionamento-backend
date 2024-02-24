package com.estacionamento.estacionamento.repository;

import com.estacionamento.estacionamento.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@org.springframework.stereotype.Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
