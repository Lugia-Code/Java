package com.lugiatracker.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="TBL_HISTORICO")
@Data
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_historico;
    
    @NotNull(message = "A data de entrada não pode ser nula.")
    @Column(name = "data_entrada")
    private Date data_entrada;
    
    @NotNull(message = "A data de saída não pode ser nula.")
    @Column(name = "data_saida")
    private Date data_saida;
    
    @ManyToOne
    @JoinColumn(name = "chassi_moto", referencedColumnName = "chassi_moto")
    private Moto moto;
    
    @ManyToOne
    @JoinColumn(name = "id_setor", referencedColumnName = "id_setor")
    private Setor setor;
    
    @ManyToOne
    @JoinColumn(name = "id_vaga", referencedColumnName = "id_vaga")
    private Vaga vaga;
}
