package br.com.fiap.universidade_fiap.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String senha;
	private String nome;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_funcao_tab", 
	joinColumns = @JoinColumn(name = "id_usuario"), 
	inverseJoinColumns = @JoinColumn(name = "id_funcao") )
	private Set<Funcao> funcoes = new HashSet<Funcao>();

	public Usuario() {

	}

	public Usuario(Long id , String senha, String nome,  Set<Funcao> funcoes) {
		super();
		this.id = id;
		this.senha = senha;
		this.nome = nome;
	
		this.funcoes = funcoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public Set<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(Set<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

}
