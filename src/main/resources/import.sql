-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');


--insert into perfis(id_usuario, perfil) values (1, 'Admin');
--insert into perfis(id_usuario, perfil) values (1, 'User');
--insert into perfis(id_usuario, perfil) values (2, 'User');

insert into pessoa (nome) values ('Alice');

insert into pessoafisica(id, cpf, sexo) values(1, '222.333.444-55', 1)

insert into usuario (login,senha, id_pessoa_fisica) values('Alice', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==',1);
