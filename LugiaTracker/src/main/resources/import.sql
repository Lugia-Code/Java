-- Inserindo dados na tabela TBL_PATIO
INSERT INTO TBL_PATIO (id_patio, nome, localizacao)VALUES(1, 'Patio Central', 'Zona Norte');
INSERT INTO TBL_PATIO (id_patio, nome, localizacao)VALUES(2, 'Patio Leste', 'Zona Leste');
INSERT INTO TBL_PATIO (id_patio, nome, localizacao)VALUES(3, 'Patio Oeste', 'Zona Oeste');

-- Inserindo dados na tabela TBL_SETOR
INSERT INTO TBL_SETOR (id_setor, nome, descricao)VALUES(1, 'Setor A', 'Setor destinado a veículos leves');
INSERT INTO TBL_SETOR (id_setor, nome, descricao)VALUES(2, 'Setor B', 'Setor destinado a veículos pesados');
INSERT INTO TBL_SETOR (id_setor, nome, descricao)VALUES(3, 'Setor C', 'Setor destinado a manutenções');

-- Inserindo dados na tabela TBL_GERENTE
INSERT INTO TBL_GERENTE (id_gerente, nome, login, senha, id_patio)VALUES(1, 'Carlos Silva', 'carlos.silva@email.com', 'Senha@123', 1);

INSERT INTO TBL_GERENTE (id_gerente, nome, login, senha, id_patio)VALUES(2, 'Ana Souza', 'ana.souza@email.com', 'Senha@123', 2);

-- Inserindo dados na tabela TBL_MOTO
INSERT INTO TBL_MOTO (chassi_moto, modelo, placa, id_setor)VALUES('9C2KC1670GR000001', 'CB 500', 'ABC-1234', 1);

INSERT INTO TBL_MOTO (chassi_moto, modelo, placa, id_setor)VALUES('JYARN23E4KA000002', 'MT-07', 'XYZ-5678', 2);

INSERT INTO TBL_MOTO (chassi_moto, modelo, placa, id_setor)VALUES('JS1GT75A5G2100003', 'GSX-R1000', 'DEF-9012', 1);
