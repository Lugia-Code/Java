insert into pessoa (nome, cpf, data_nascimento, nacionalidade, email) values ('Pessoa 1','111.222.333-44','2003-01-01','BRASILEIRA','pessoa1@fiap.com.br'); 

insert into pessoa (nome, cpf, data_nascimento, nacionalidade, email) values ('Pessoa 2','222.222.333-44','2004-01-01','NORTE_AMERICANA','pessoa2@fiap.com.br'); 

insert into pessoa (nome, cpf, data_nascimento, nacionalidade, email) values ('Pessoa 3','333.222.333-44','2005-01-01','ITALIANA','pessoa3@fiap.com.br'); 

insert into pessoa (nome, cpf, data_nascimento, nacionalidade, email) values ('Pessoa 4','444.222.333-44','2006-01-01','BRASILEIRA','pessoa4@fiap.com.br'); 

insert into pessoa (nome, cpf, data_nascimento, nacionalidade, email) values ('Pessoa 5','555.222.333-44','2007-01-01','CHILENA','pessoa5@fiap.com.br'); 

insert into discente (id_pessoa,rm,status,nivel) values (1,1234,'ATIVO','TECNICO');

insert into discente (id_pessoa,rm,status,nivel) values (2,1235,'INATIVO','MBA');

insert into discente (id_pessoa,rm,status,nivel) values (3,1236,'TRANCADO','TECNOLOGO');

insert into discente (id_pessoa,rm,status,nivel) values (4,1237,'FORMADO','BACHARELADO');

insert into discente (id_pessoa,rm,status,nivel) values (5,1238,'ATIVO','MESTRADO');

-- username: admin e senha: admin
insert into usuario(username,senha,img_perfil,nome_perfil) values('admin','$2a$12$YcxBeQKXPK.06QNckf.YMeGVm8h.EazFMyURfIDFRHc554uvM3v9K','https://i0.wp.com/media.tumblr.com/tumblr_lga4hf2NWD1qfdzua.jpg','Administrador FIAP');

insert into funcao(nome) values('ADMIN');
insert into funcao(nome) values('DISCENTE');
insert into funcao(nome) values('PROFESSOR');
insert into funcao(nome) values('COORDENADOR');
insert into funcao(nome) values('DIRETOR');
insert into funcao(nome) values('SUPORTE');
insert into funcao(nome) values('SERVICOS');

insert into usuario_funcao_tab(id_usuario,id_funcao) values(1,1);