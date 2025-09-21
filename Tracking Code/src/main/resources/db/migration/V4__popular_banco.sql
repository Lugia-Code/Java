-- Criar pessoa associada ao admin
insert into pessoa (nome, cpf, data_nascimento, email) 
values ('Admin FIAP','000.000.000-00','1990-01-01','admin@fiap.com.br');

-- Criar usuário admin com senha "admin" (hash BCrypt)
insert into usuario (nome, senha) 
values (
    'admin',
    '$2a$12$YcxBeQKXPK.06QNckf.YMeGVm8h.EazFMyURfIDFRHc554uvM3v9K' -- senha: admin
);

-- Criar role GERENTE (se ainda não existir)
insert into funcao (nome) values ('GERENTE');
insert into funcao (nome) values ('OPERADOR');
insert into funcao (nome) values ('AUXILIAR');


-- Vincular o usuário admin à role GERENTE
insert into usuario_funcao_tab (id_usuario, id_funcao) 
values (
    (select id from usuario where nome = 'admin'),
    (select id from funcao where nome = 'GERENTE')
);
