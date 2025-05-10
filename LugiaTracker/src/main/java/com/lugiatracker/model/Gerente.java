package com.lugiatracker.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Entity
@Table(name="TBL_GERENTE")
@Data
public class Gerente extends RepresentationModel<Gerente> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_gerente;
	
	@NotEmpty(message= "Não é permitido a inserção de um gerente sem seu nome.")
	@Column(name="nome")
	private String nome;
	
	@NotEmpty(message= "Não é permitido a inserção de gerente com login vazio.")
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Formato de email inválido.")
	@Column(name="login")
	private String login;
	
	@NotEmpty(message= "Não é permitido a inserção de um gerente sem senha.")
	@Pattern(
		    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
		    message = "A senha deve ter no mínimo 8 caracteres, com ao menos uma letra maiúscula, uma minúscula, um número e um caractere especial."
		)
	@Column(name="senha")
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="id_patio")
	private Patio patio;
}
