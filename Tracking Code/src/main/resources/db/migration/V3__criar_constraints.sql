-- Adicionar constraints de FK
ALTER TABLE usuario_funcao_tab
ADD CONSTRAINT fk_usuario_funcao_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id);

ALTER TABLE usuario_funcao_tab
ADD CONSTRAINT fk_usuario_funcao_funcao FOREIGN KEY (id_funcao) REFERENCES funcao(id);

ALTER TABLE usuario_setor_tab
ADD CONSTRAINT fk_usuario_setor_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id);

ALTER TABLE usuario_setor_tab
ADD CONSTRAINT fk_usuario_setor_setor FOREIGN KEY (id_setor) REFERENCES setor(id);
