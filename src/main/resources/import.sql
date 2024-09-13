CREATE EXTENSION IF NOT EXISTS unaccent;
INSERT INTO public.users (id, cep, longitude, latitude, create_date, email, is_natural_person, last_modified_date, "name", "password", perfil, person_registration, status) VALUES(2, '77021-688', 'string', 'string', '2024-04-07 14:54:50.297', 'luiz@gmail.com', true, '2024-04-07 14:54:50.297', 'Luiz', '$2a$10$pphaZwfDn.sigeA02u5RR.34qnDDWxlQcxDFIS7MXZkMgkEhKY8P2', 'ADMIN', '95851082089', 'ACTIVE');
INSERT INTO public.users (id, cep, longitude, latitude, create_date, email, is_natural_person, last_modified_date, "name", "password", perfil, person_registration, status) VALUES(1, '77021-688', 'string', 'string', '2024-04-15 22:36:55.509', 'luiz2@gmail.com', true, '2024-04-15 22:36:55.509', 'Luiz2', '$2a$10$LojmLMxZCUMiuvg66cdZpe9CXr7gr8kjA9ClD.KUxD4mdWl6aKsb6', 'USER', '55484654220', 'ACTIVE');


-- Palmas, Tocantins (3 registros)
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Honda Civic', 2018, 2019, 85000, 'Completo com todos os opcionais.', 45000, -48.333121, -10.184358, 1, 'SEDAN', 'GASOLINA', 'SEMI_NOVO');
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Toyota Corolla', 2020, 2021, 90000, 'Pouco uso, ainda na garantia.', 15000, -48.332867, -10.183752, 1, 'SEDAN', 'GAS_NATURAL', 'SEMI_NOVO');
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Ford Ranger', 2019, 2020, 120000, 'Ideal para trabalho pesado.', 85000, -48.327734, -10.207385, 1, 'PICKUP', 'DIESEL', 'USADO');

-- Porto Nacional, Tocantins (3 registros)
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Chevrolet Onix', 2021, 2022, 60000, 'Econômico e compacto.', 10000, -48.410030, -10.702759, 1, 'HATCH', 'ETANOL', 'NOVO');
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Renault Kwid', 2019, 2020, 35000, 'Carro econômico, ideal para a cidade.', 50000, -48.410793, -10.704446, 1, 'HATCH', 'ELETRICO', 'USADO');
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('BMW X5', 2018, 2019, 250000, 'Luxo e conforto com tecnologia avançada.', 30000, -48.412624, -10.702319, 1, 'SUV', 'ELETRICO', 'SEMI_NOVO');

-- Paraíso do Tocantins, Tocantins (2 registros)
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Mercedes Classe C', 2017, 2018, 220000, 'Elegância e potência.', 40000, -48.898982, -10.175824, 1, 'SEDAN', 'GASOLINA', 'USADO');
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Volkswagen Amarok', 2020, 2021, 180000, 'Robusta e pronta para qualquer terreno.', 25000, -48.897783, -10.180428, 1, 'PICKUP', 'DIESEL', 'NOVO');

-- Araguaína, Tocantins (2 registros)
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Tesla Model X', 2021, 2022, 500000, 'Veículo elétrico inovador.', 5000, -48.208714, -7.191105, 1, 'SUV', 'ELETRICO', 'NOVO');
INSERT INTO Oferta (modelo, ano_fabricacao, ano_modelo, preco, descricao, quilometragem, longitude, latitude, user_id, tipo_veiculo, combustivel, estado_veiculo) VALUES ('Nissan Leaf', 2019, 2020, 90000, '100% elétrico e zero emissões.', 20000, -48.207282, -7.189721, 1, 'HATCH', 'ELETRICO', 'SEMI_NOVO');
