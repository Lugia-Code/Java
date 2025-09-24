package br.com.fiap.universidade_fiap.model;

public enum EnumSetor {
    MANUTENCAO("Manutenção"),
    PENDENTES("Pendentes"),
    SEM_PLACA("Sem Placa"),
    REPARO_SIMPLES("Reparo Simples"),
    DANOS_GRAVES("Danos Graves"),
    PRONTAS_PARA_ALUGAR("Prontas para Alugar"),
    MOTOR_DEFEITUOSO("Motor Defeituoso"),
    AGENDADAS_PARA_MANUTENCAO("Agendadas para Manutenção");

    private final String descricao;

    EnumSetor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

