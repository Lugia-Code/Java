package com.lugiatracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;



@Table(name="usuario")
public class Funcionarios {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_funcionario;
	
	
    
    @NotEmpty(message = "Não é permitido o cadastro de um funcionario sem nome.")
    @Column(nullable = false)
    private String nome;
    
    
    @NotEmpty(message= "Não é permitido a inserção de gerente com login vazio.")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Formato de email inválido.")
    @Column(name="login")
    private String email;
    
    
    @NotEmpty(message= "Não é permitido a inserção de um gerente sem senha.")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
        message = "A senha deve ter no mínimo 8 caracteres, com ao menos uma letra maiúscula, uma minúscula, um número e um caractere especial."
    )
    @Column(name="senha")
    private String senha;
    
    
    @NotEmpty(message = "Não é permitido o cadastro de um funcionario sem função.")
    @Column(nullable = false)
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
	
	
	public Funcionarios() {
		super();
	}
	public Funcionarios(String nome, String email, String senha, String funcao) {
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
