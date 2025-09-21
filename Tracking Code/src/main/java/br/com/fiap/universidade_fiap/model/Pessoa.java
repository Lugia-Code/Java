package br.com.fiap.universidade_fiap.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "O campo nome é obrigatório")
	@Size(min = 3, max = 80, message = "O campo nome deve ter, ao menos, "
			+ "3 caracteres e, no máximo, 80 caracteres")
	private String nome;
	@CPF(message = "O valor de CPF informado é inválido")
	private String cpf;
	@DateTimeFormat(iso = ISO.DATE)
	@Past(message = "A data de nascimento é inválida")
	private LocalDate dataNascimento;

	@Email(message = "O e-mail é inválido")
	private String email;

	public Pessoa() {

	}
	
	
	

	public Pessoa(Long id,
			@NotEmpty(message = "O campo nome é obrigatório") @Size(min = 3, max = 80, message = "O campo nome deve ter, ao menos, 3 caracteres e, no máximo, 80 caracteres") String nome,
			@CPF(message = "O valor de CPF informado é inválido") String cpf,
			@Past(message = "A data de nascimento é inválida") LocalDate dataNascimento,
			@Email(message = "O e-mail é inválido") String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	
	
}
