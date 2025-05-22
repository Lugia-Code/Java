package com.lugiatracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="TBL_PATIO")
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_patio;

    @NotEmpty(message= "Não é permitido a inserção de um patio sem nome.")
    @Column(name="nome")
    private String nome;

    @NotEmpty(message= "Não é permitido a inserção de um patio sem a  localizacao.")
    @Column(name="localizacao")
    private String localizacao;

    // Construtor vazio
    public Patio() {}

    // Construtor cheio
    public Patio(Integer id_patio, String nome, String localizacao) {
        this.id_patio = id_patio;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    // Getters e Setters
    public Integer getId_patio() {
        return id_patio;
    }

    public void setId_patio(Integer id_patio) {
        this.id_patio = id_patio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Patio{" +
            "id_patio=" + id_patio +
            ", nome='" + nome + '\'' +
            ", localizacao='" + localizacao + '\'' +
            '}';
    }
}
