CREATE EXTENSION IF NOT EXISTS unaccent;
INSERT INTO public.users (id, cep, longitude, latitude, create_date, email, is_natural_person, last_modified_date, "name", "password", perfil, person_registration, status) VALUES(2, '77021-688', 'string', 'string', '2024-04-07 14:54:50.297', 'luiz@gmail.com', true, '2024-04-07 14:54:50.297', 'Luiz', '$2a$10$pphaZwfDn.sigeA02u5RR.34qnDDWxlQcxDFIS7MXZkMgkEhKY8P2', 'ADMIN', '95851082089', 'ACTIVE');
INSERT INTO public.users (id, cep, longitude, latitude, create_date, email, is_natural_person, last_modified_date, "name", "password", perfil, person_registration, status) VALUES(1, '77021-688', 'string', 'string', '2024-04-15 22:36:55.509', 'luiz2@gmail.com', true, '2024-04-15 22:36:55.509', 'Luiz2', '$2a$10$LojmLMxZCUMiuvg66cdZpe9CXr7gr8kjA9ClD.KUxD4mdWl6aKsb6', 'USER', '55484654220', 'ACTIVE');



INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Chevrolet Onix', 2021, 2022, 60000, 'Econômico e compacto.', 10000, -48.3921, -10.6989, 1, 'HATCH', 'GASOLINA', 'SEMI_NOVO');
insert into oferta_caracteristica (oferta_id, caracteristica) values (1, 'VIDROS_ELETRICOS');
insert into oferta_caracteristica (oferta_id, caracteristica) values (1, 'SENSOR_ESTACIONAMENTO');
insert into oferta_caracteristica (oferta_id, caracteristica) values (1, 'CAMERA_RE' );

INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Renault Logan', 2019, 2020, 35000, 'Carro econômico, ideal para a cidade.', 50000, -48.3976,  -10.6944, 1, 'SEDAN', 'ELETRICO', 'USADO');
insert into oferta_caracteristica (oferta_id, caracteristica) values (2, 'VIDROS_ELETRICOS');
insert into oferta_caracteristica (oferta_id, caracteristica) values (2, 'DIRECAO_HIDRAULICA');
insert into oferta_caracteristica (oferta_id, caracteristica) values (2, 'AR_CONDICIONADO' );

INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('BMW E30', 2005, 2006, 250000, 'Luxo e conforto com tecnologia avançada.', 100000, -48.3981,  -10.7016, 1, 'SUV', 'ELETRICO', 'USADO');
insert into oferta_caracteristica (oferta_id, caracteristica) values (3, 'VIDROS_ELETRICOS');
insert into oferta_caracteristica (oferta_id, caracteristica) values (3, 'SISTEMA_SOM_PREMIUM');
insert into oferta_caracteristica (oferta_id, caracteristica) values (3, 'ASSISTENTE_FAIXA' );

-- Palmas, Tocantins (3 registros)
-- -- Porto Nacional, Tocantins (3 registros)
-- INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Catedral - Porto Nacional -  Onix', 2021, 2022, 60000, 'Econômico e compacto.', 10000, -48.3921, -10.6989, 1, 'HATCH', 'ETANOL', 'NOVO');
-- INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Igreja de São José - Porto Nacional -   Kwid', 2019, 2020, 35000, 'Carro econômico, ideal para a cidade.', 50000, -48.3976,  -10.6944, 1, 'HATCH', 'ELETRICO', 'USADO');
-- INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Museu Histórico Nacional -  Porto Nacional - BMW', 2018, 2019, 250000, 'Luxo e conforto com tecnologia avançada.', 30000, -48.3981,  -10.7016, 1, 'SUV', 'ELETRICO', 'SEMI_NOVO');
--
--
-- -- Araguaína, Tocantins (2 registros)
-- INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Estádio Mirandão -Araguaina -  Tesla', 2021, 2022, 500000, 'Veículo elétrico inovador.', 5000, -48.2047, -7.1867, 1, 'SUV', 'ELETRICO', 'NOVO');
-- INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Parque Cimpa - Araguaína - Nissan', 2019, 2020, 90000, '100% elétrico e zero emissões.', 20000, -48.2068,  -7.2003, 1, 'HATCH', 'ELETRICO', 'SEMI_NOVO');


-- Paraíso do Tocantins, Tocantins (2 registros)
--INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Mercedes Classe C', 2017, 2018, 220000, 'Elegância e potência.', 40000, -48.898982, -10.175824, 1, 'SEDAN', 'GASOLINA', 'USADO');
--INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Volkswagen Amarok', 2020, 2021, 180000, 'Robusta e pronta para qualquer terreno.', 25000, -48.897783, -10.180428, 1, 'PICKUP', 'DIESEL', 'NOVO');
