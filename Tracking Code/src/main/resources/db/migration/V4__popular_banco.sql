-- Criar pessoa admin
INSERT INTO pessoa (nome, cpf, data_nascimento, email) 
VALUES ('Admin FIAP','000.000.000-00','1990-01-01','admin@fiap.com.br');

-- Criar usuário admin com senha "admin" (hash BCrypt)
INSERT INTO usuario (nome, senha) 
VALUES ('admin','$2a$12$YcxBeQKXPK.06QNckf.YMeGVm8h.EazFMyURfIDFRHc554uvM3v9K');

-- Criar roles
INSERT INTO funcao (nome) VALUES ('GERENTE');
INSERT INTO funcao (nome) VALUES ('OPERADOR');
INSERT INTO funcao (nome) VALUES ('AUXILIAR');

-- Popular setores (sem acento)
INSERT INTO setor (nome) VALUES ('MANUTENCAO');
INSERT INTO setor (nome) VALUES ('PENDENTES');
INSERT INTO setor (nome) VALUES ('SEM_PLACA');
INSERT INTO setor (nome) VALUES ('REPARO_SIMPLES');
INSERT INTO setor (nome) VALUES ('DANOS_GRAVES');
INSERT INTO setor (nome) VALUES ('PRONTAS_PARA_ALUGAR');
INSERT INTO setor (nome) VALUES ('MOTOR_DEFEITUOSO');
INSERT INTO setor (nome) VALUES ('AGENDADAS_PARA_MANUTENCAO');

-- Vincular usuário admin à role GERENTE
INSERT INTO usuario_funcao_tab (id_usuario, id_funcao) 
VALUES (
    (SELECT id FROM usuario WHERE nome = 'admin'),
    (SELECT id FROM funcao WHERE nome = 'GERENTE')
);
