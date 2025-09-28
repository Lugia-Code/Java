-- Endereço
INSERT INTO endereco (cep, logradouro, complemento, unidade, bairro, localidade, uf, estado, regiao)
VALUES ('01001-000', 'Praça da Sé', 'lado ímpar', '', 'Sé', 'São Paulo', 'SP', 'São Paulo', 'Sudeste');

-- Pessoa Admin
INSERT INTO pessoa (nome, cpf, data_nascimento, email)
VALUES ('Admin FIAP','000.000.000-00','1990-01-01','admin@fiap.com.br');

-- Usuário Admin (senha já com hash BCrypt)
INSERT INTO usuario (nome, senha)
VALUES ('admin','$2a$12$YcxBeQKXPK.06QNckf.YMeGVm8h.EazFMyURfIDFRHc554uvM3v9K');

-- Funções
INSERT INTO funcao (nome) VALUES 
('GERENTE'), ('OPERADOR'), ('AUXILIAR');

-- Setores (nome = enum, descricao = humanizada)
INSERT INTO setor (nome, descricao) VALUES
('MANUTENCAO','Manutenção'),
('PENDENTES','Pendentes'),
('SEM_PLACA','Sem Placa'),
('REPARO_SIMPLES','Reparo Simples'),
('DANOS_GRAVES','Danos Graves'),
('PRONTAS_PARA_ALUGAR','Prontas para Alugar'),
('MOTOR_DEFEITUOSO','Motor Defeituoso'),
('AGENDADAS_PARA_MANUTENCAO','Agendadas para Manutenção');

-- Vincular usuário admin à função GERENTE
INSERT INTO usuario_funcao_tab (id_usuario, id_funcao)
VALUES (
    (SELECT id FROM usuario WHERE nome = 'admin'),
    (SELECT id FROM funcao WHERE nome = 'GERENTE')
);

-- Vincular usuário admin ao endereço
INSERT INTO usuario_endereco_tab (id_usuario, id_endereco)
VALUES (
    (SELECT id FROM usuario WHERE nome = 'admin'),
    (SELECT id FROM endereco WHERE cep = '01001-000')
);
