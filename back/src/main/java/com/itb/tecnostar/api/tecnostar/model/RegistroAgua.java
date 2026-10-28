package com.itb.tecnostar.api.tecnostar.model;

import jakarta.persistence.*;

import java.time.LocalDate;

// Copos de água bebidos por dia (tela "Controle de água"). Um registro por usuário por dia.
@Entity
@Table(name = "registros_agua",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "data_registro"}))
public class RegistroAgua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @Column(nullable = false)
    private int copos;

    public RegistroAgua() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }

    public int getCopos() { return copos; }
    public void setCopos(int copos) { this.copos = copos; }
}
