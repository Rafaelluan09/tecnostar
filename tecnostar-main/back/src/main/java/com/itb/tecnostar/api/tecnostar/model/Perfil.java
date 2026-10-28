package com.itb.tecnostar.api.tecnostar.model;

import jakarta.persistence.*;

// Dados e objetivos do usuário (tela "Seus dados e objetivos"). Relação 1 para 1 com Usuario.
@Entity
@Table(name = "perfis")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(nullable = false)
    private Double peso;          // kg

    @Column(nullable = false)
    private Double altura;        // cm

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false, length = 1)
    private String genero;        // "f" ou "m"

    @Column(name = "nivel_atividade", nullable = false)
    private Double nivelAtividade; // 1.2 | 1.375 | 1.55 | 1.725

    @Column(nullable = false, length = 20)
    private String objetivo;      // emagrecer | massa | manter | saude

    @Column(nullable = false, length = 20)
    private String preferencia;   // comum | vegetariano

    public Perfil() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Double getNivelAtividade() { return nivelAtividade; }
    public void setNivelAtividade(Double nivelAtividade) { this.nivelAtividade = nivelAtividade; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getPreferencia() { return preferencia; }
    public void setPreferencia(String preferencia) { this.preferencia = preferencia; }
}
