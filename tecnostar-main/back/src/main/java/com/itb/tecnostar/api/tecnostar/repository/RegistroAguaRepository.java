package com.itb.tecnostar.api.tecnostar.repository;

import com.itb.tecnostar.api.tecnostar.model.RegistroAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroAguaRepository extends JpaRepository<RegistroAgua, Long> {

    Optional<RegistroAgua> findByUsuarioIdAndDataRegistro(Long usuarioId, LocalDate dataRegistro);

    List<RegistroAgua> findByUsuarioIdOrderByDataRegistroDesc(Long usuarioId);

    void deleteByUsuarioId(Long usuarioId);
}
