package com.lugiatracker.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "TBL_MOTO")
public class Moto extends RepresentationModel<Moto> {

    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
    @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "Formato de chassi inválido. O chassi deve ter 17 caracteres alfanuméricos.")
    @Column(name="chassi_moto", length = 17)
    private String chassimoto;

    @NotEmpty(message= "Não é permitido a inserção de uma moto sem o modelo")
    @Column(name="modelo")
    private String modelo;

    @NotEmpty(message= "Não é permitido a inserção de uma moto sem a placa")
    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$", message = "Formato da placa inválido. Exemplo válido: ABC-1234")
    @Column(name="placa")
    private String placa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChassimoto() {
		return chassimoto;
	}

	public void setChassimoto(String chassimoto) {
		this.chassimoto = chassimoto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Moto() {
		super();
	}

	public Moto(Long id,
			@Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "Formato de chassi inválido. O chassi deve ter 17 caracteres alfanuméricos.") String chassimoto,
			@NotEmpty(message = "Não é permitido a inserção de uma moto sem o modelo") String modelo,
			@NotEmpty(message = "Não é permitido a inserção de uma moto sem a placa") @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$", message = "Formato da placa inválido. Exemplo válido: ABC-1234") String placa) {
		super();
		this.id = id;
		this.chassimoto = chassimoto;
		this.modelo = modelo;
		this.placa = placa;
	}

	@Override
	public String toString() {
		return "Moto [id=" + id + ", chassimoto=" + chassimoto + ", modelo=" + modelo + ", placa=" + placa + "]";
	}

    
   
    
    
}

