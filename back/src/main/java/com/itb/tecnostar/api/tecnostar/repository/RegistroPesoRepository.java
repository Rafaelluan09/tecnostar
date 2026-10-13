package com.itb.tecnostar.api.tecnostar.repository;

import com.itb.tecnostar.api.tecnostar.model.RegistroPeso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPesoRepository extends JpaRepository<RegistroPeso, Long> {

    List<RegistroPeso> findByUsuarioIdOrderByDataRegistroAscIdAsc(Long usuarioId);

    boolean existsByUsuarioId(Long usuarioId);

    void deleteByUsuarioId(Long usuarioId);
}
