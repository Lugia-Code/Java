package com.lugiatracker.dto;

import org.springframework.hateoas.RepresentationModel;
import com.lugiatracker.model.Gerente;
import lombok.Data;

@Data
public class GerenteDTO extends RepresentationModel<GerenteDTO> {

    private Integer id_gerente;
    private String nome;
    private String login;
    private String senha;

    public GerenteDTO(Gerente gerente) {
        this.id_gerente = gerente.getId_gerente();
        this.nome = gerente.getNome();
        this.login = gerente.getLogin();
        this.senha = gerente.getSenha();
    }

    public GerenteDTO(Integer id_gerente, String nome, String login, String senha) {
        this.id_gerente = id_gerente;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
