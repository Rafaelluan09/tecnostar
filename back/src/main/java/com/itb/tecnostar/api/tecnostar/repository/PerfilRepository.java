package com.itb.tecnostar.api.tecnostar.repository;

import com.itb.tecnostar.api.tecnostar.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findByUsuarioId(Long usuarioId);

    void deleteByUsuarioId(Long usuarioId);
}
