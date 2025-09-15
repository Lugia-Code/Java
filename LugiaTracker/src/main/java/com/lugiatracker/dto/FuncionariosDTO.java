package com.lugiatracker.dto;

import org.springframework.hateoas.RepresentationModel;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class FuncionariosDTO extends RepresentationModel<FuncionariosDTO> {


    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;
    
    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;
    
    private String senha;
    private String funcao;
    
    
    
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	

	public FuncionariosDTO() {
		super();
	}
	
	
	
	public FuncionariosDTO(String nome, String email, String senha, String funcao) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.funcao = funcao;
	}
	@Override
	public String toString() {
		return "Funcionarios [nome=" + nome + ", email=" + email + ", senha=" + senha + ", funcao=" + funcao + "]";
	}
    
    
    
    
    
    
    
	
}
