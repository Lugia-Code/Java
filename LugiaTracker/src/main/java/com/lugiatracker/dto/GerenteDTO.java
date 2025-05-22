package com.lugiatracker.dto;

import org.springframework.hateoas.RepresentationModel;

import com.lugiatracker.model.Gerente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class GerenteDTO extends RepresentationModel<GerenteDTO> {

    private Integer id_gerente;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;
    
    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String login;
    private String senha;
    
    
    
    

    public Integer getId_gerente() {
		return id_gerente;
	}

	public void setId_gerente(Integer id_gerente) {
		this.id_gerente = id_gerente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

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

	public GerenteDTO() {
		super();
	}

	@Override
	public String toString() {
		return "GerenteDTO [id_gerente=" + id_gerente + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ "]";
	}
    
    
	
}
