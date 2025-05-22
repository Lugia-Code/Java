-- Inserindo dados na tabela TBL_PATIO
INSERT INTO TBL_PATIO (id_patio, nome, localizacao)VALUES(1, 'Patio Central', 'Zona Norte');
INSERT INTO TBL_PATIO (id_patio, nome, localizacao)VALUES(2, 'Patio Leste', 'Zona Leste');
INSERT INTO TBL_PATIO (id_patio, nome, localizacao)VALUES(3, 'Patio Oeste', 'Zona Oeste');

-- Inserindo dados na tabela TBL_GERENTE
INSERT INTO TBL_GERENTE (id_gerente, nome, login, senha, id_patio)VALUES(1, 'Carlos Silva', 'carlos.silva@email.com', 'Senha@123', 1);

INSERT INTO TBL_GERENTE (id_gerente, nome, login, senha, id_patio)VALUES(2, 'Ana Souza', 'ana.souza@email.com', 'Senha@123', 2);

-- Inserindo dados na tabela TBL_MOTO

INSERT INTO TBL_MOTO (chassi_moto, modelo, placa) VALUES ('JYARN23E4KA000002', 'MT-07', 'XYZ-5678');
INSERT INTO TBL_MOTO (chassi_moto, modelo, placa) VALUES ('JS1GT75A5G2100003', 'GSX-R1000', 'DEF-9012');
