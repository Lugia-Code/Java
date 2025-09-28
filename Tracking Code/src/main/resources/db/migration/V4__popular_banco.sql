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



-- Popular setores (nome SEM acento; descricao humanizada)
INSERT INTO setor (nome, descricao) VALUES ('MANUTENCAO', 'Motos enviadas para manutencao');
INSERT INTO setor (nome, descricao) VALUES ('PENDENTES', 'Motos com status pendentes');
INSERT INTO setor (nome, descricao) VALUES ('SEM_PLACA', 'Motos sem placas');
INSERT INTO setor (nome, descricao) VALUES ('REPARO_SIMPLES', 'Motos em reparo simples');
INSERT INTO setor (nome, descricao) VALUES ('DANOS_GRAVES', 'Motos com danos graves');
INSERT INTO setor (nome, descricao) VALUES ('PRONTAS_PARA_ALUGAR', 'Motos prontas para alugar');
INSERT INTO setor (nome, descricao) VALUES ('MOTOR_DEFEITUOSO', 'Motos com motor defeituoso');
INSERT INTO setor (nome, descricao) VALUES ('AGENDADAS_PARA_MANUTENCAO', 'Motos agendadas para manutencao');

-- Vincular usuário admin à role GERENTE
INSERT INTO usuario_funcao_tab (id_usuario, id_funcao) 
VALUES (
    (SELECT id FROM usuario WHERE nome = 'admin'),
    (SELECT id FROM funcao WHERE nome = 'GERENTE')
);


INSERT INTO usuario_endereco_tab (id_usuario, id_endereco)
VALUES (
    (SELECT id FROM usuario WHERE nome = 'admin'),
    (SELECT id FROM endereco WHERE cep = '01001-000')
);