-- Import de Gerentes
INSERT INTO gerentes (id, nome, email, telefone) VALUES (1, 'Carlos Silva', 'carlos.silva@email.com', '11999999999');
INSERT INTO gerentes (id, nome, email, telefone) VALUES (2, 'Ana Souza', 'ana.souza@email.com', '11988888888');

-- Import de Motos
INSERT INTO motos (id, marca, modelo, ano, placa, id_gerente) VALUES (1, 'Honda', 'CB 500', 2021, 'ABC-1234', 1);
INSERT INTO motos (id, marca, modelo, ano, placa, id_gerente) VALUES (2, 'Yamaha', 'MT-07', 2022, 'XYZ-5678', 2);
INSERT INTO motos (id, marca, modelo, ano, placa, id_gerente) VALUES (3, 'Suzuki', 'GSX-R1000', 2020, 'DEF-9012', 1);
