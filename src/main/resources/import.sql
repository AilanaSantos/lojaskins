-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');



--insert into perfis(id_usuario, perfil) values (1, 'User');
--insert into perfis(id_usuario, perfil) values (2, 'User');

--insert into pessoa (nome) values ('Ailana');
--------------------------------------------------------------------------------------
insert into estado (nome, sigla) values( 'Tocantins', 'TO');
insert into estado (nome, sigla) values( 'Goiás', 'GO');
insert into estado (nome, sigla) values( 'São Paulo', 'SP');
insert into estado (nome, sigla) values( 'Rio de Janeiro', 'RJ');
insert into estado (nome, sigla) values( 'Pará', 'PA');
---------------------------------------------------------------------------------------
insert into cidade (nome, id_estado) values( 'Palmas', 1);
insert into cidade (nome, id_estado) values( 'Paraiso do Tocantins', 1);
----------------------------------------------------------------------------------

insert into public.endereco(
	cidade_id, dataalteracao, datainclusao, id, bairro, cep, complemento, logradouro, numero, principal, rua)
	values (1,'10-06-2013' , '11-06-2013', 1, 'Setor Aeroporto', '78900000', 'casa', 'Setor Aeroporto', '4567', 'Principal Aero', 'Sua avenida');

------------------------------------------------------------------
insert into public.telefone(
	dataalteracao, datainclusao, id, codigoarea, numero)
	values ('10-06-2013','11-06-2013', 1, '63', '5543537573');

------------------------------------------------------------------------

insert into public.compra(
	date, totalcompra, dataalteracao, datainclusao, id)
	values ('10-06-2013', 300.00, '10-06-2013', '11-06-2013', 1);
-----------------------------------------------------------------------

insert into public.pagamento(
	valor, compra_id, dataalteracao, datainclusao, id)
	values (200.00, 1,'10-06-2013', '10-06-2013', 1);


-- insert into public.skins(
-- 	raridade, tipoarma, dataalteracao, datainclusao, id, nome, tipodesgate)
-- 	values (1, 1, '10-06-2013', '11-06-2013', 1, 'dragao', 'desgatada');

-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- insert into public.produto(
-- 	estoque, preco, dataalteracao, datainclusao, id, descricao, nome)
-- 	values (20, 240.00,'10-06-2013' , '11-06-2013', 1, 'Skins Esmeralda', 'Ailana');


--------------------------------------------
insert into public.usuario(
	compra_id, dataalteracao, datainclusao, id, pagamento_id, cpf, login, nome, nomeimagem, senha)
	values (1,'10-06-2013', '11-06-2013', 1 , 1,'423642364', 'Ailana', 'Ailana', '?', 'Otf1xXVOiZtg2D8mH2sbbBykcKkF9SgzhC0T4kfegQ52WOgcrusaUCCZqnLftPuIvMN84o24bRMDHm9cGAESYw==');


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 insert into public.perfis(
 	id_usuario, perfil)
	values (1, 'admin');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------


--insert into usuario (login,senha) values(1,'Ailana', 'Otf1xXVOiZtg2D8mH2sbbBykcKkF9SgzhC0T4kfegQ52WOgcrusaUCCZqnLftPuIvMN84o24bRMDHm9cGAESYw==');

--insert into perfis(id_usuario, perfil) values (1, 'Admin');

--insert into skins (nome, tipo) values ('Dragao Esmeralda', 'Consumidor');