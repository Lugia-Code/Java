package com.lugiatracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Table(name= "TBL_CAMERA")
@Data
public class Camera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_camera;
	
	@NotEmpty(message="Não é possível fazer a inserção de uma câmera sem uma posição.")
	@Column(name="posicao")
	private String posicao;
	
	@NotEmpty(message="Não é possível fazer a inserção de uma câmera sem o IP da stream de vídeo.")
	@Column(name="ip_stream")
	private String ip_stream;
	
	@ManyToOne
	@JoinColumn(name= "id_patio")
	private Patio patio;
}
