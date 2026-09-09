-- 1. tb_user (10 registros)
INSERT INTO tb_user (username, password, role) VALUES ('vet1@clyvopaws.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'VETERINARIO');
INSERT INTO tb_user (username, password, role) VALUES ('vet2@clyvopaws.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'VETERINARIO');
INSERT INTO tb_user (username, password, role) VALUES ('vet3@clyvopaws.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'VETERINARIO');
INSERT INTO tb_user (username, password, role) VALUES ('vet4@clyvopaws.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'VETERINARIO');
INSERT INTO tb_user (username, password, role) VALUES ('vet5@clyvopaws.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'VETERINARIO');
INSERT INTO tb_user (username, password, role) VALUES ('tutor1@email.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'TUTOR');
INSERT INTO tb_user (username, password, role) VALUES ('tutor2@email.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'TUTOR');
INSERT INTO tb_user (username, password, role) VALUES ('tutor3@email.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'TUTOR');
INSERT INTO tb_user (username, password, role) VALUES ('tutor4@email.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'TUTOR');
INSERT INTO tb_user (username, password, role) VALUES ('tutor5@email.com', '$2a$10$z2ZH77jEKSUNB.wIqUWIlOOvQ6g8e655QuPx4KyN7Cw1KcK55MN86', 'TUTOR');

-- 2. tb_clinica (10 registros)
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Clínica Paws Care Matriz', '11.111.111/0001-11', '11911111111', 'matriz@pawscare.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Hospital Veterinário Vida Animal', '22.222.222/0001-22', '11922222222', 'contato@vidaanimal.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Pet Care Morumbi', '33.333.333/0001-33', '11933333333', 'morumbi@petcare.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Clinica Vet Paulistana', '44.444.444/0001-44', '11944444444', 'paulistana@vet.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Amigos dos Pets Centro', '55.555.555/0001-55', '11955555555', 'centro@amigospets.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Clinica Vet Pinheiros', '66.666.666/0001-66', '11966666666', 'pinheiros@vet.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Veterinária 4 Patas', '77.777.777/0001-77', '11977777777', 'contato@4patas.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Pet Saude Jardins', '88.888.888/0001-88', '11988888888', 'jardins@petsaude.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Clinica Vet Paulista', '99.999.999/0001-99', '11999999999', 'paulista@vet.com');
INSERT INTO tb_clinica (nome, cnpj, telefone, email) VALUES ('Zoovet Hospital', '10.000.000/0001-10', '11900000000', 'contato@zoovet.com');

-- 3. tb_tutor (10 registros)
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Ana Souza', '111.111.111-11', '11911111111', 'tutor1@email.com', (SELECT id FROM tb_user WHERE username = 'tutor1@email.com'), 'Rua A', '10', 'Bairro 1', 'São Paulo', 'SP', '01001-001');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Bruno Lima', '222.222.222-22', '11922222222', 'tutor2@email.com', (SELECT id FROM tb_user WHERE username = 'tutor2@email.com'), 'Rua B', '20', 'Bairro 2', 'São Paulo', 'SP', '01001-002');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Carla Dias', '333.333.333-33', '11933333333', 'tutor3@email.com', (SELECT id FROM tb_user WHERE username = 'tutor3@email.com'), 'Rua C', '30', 'Bairro 3', 'São Paulo', 'SP', '01001-003');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Daniel Rocha', '444.444.444-44', '11944444444', 'tutor4@email.com', (SELECT id FROM tb_user WHERE username = 'tutor4@email.com'), 'Rua D', '40', 'Bairro 4', 'São Paulo', 'SP', '01001-004');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Elaine Martins', '555.555.555-55', '11955555555', 'tutor5@email.com', (SELECT id FROM tb_user WHERE username = 'tutor5@email.com'), 'Rua E', '50', 'Bairro 5', 'São Paulo', 'SP', '01001-005');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Fabio Guedes', '666.666.666-66', '11966666666', 'tutor6@email.com', NULL, 'Rua F', '60', 'Bairro 6', 'São Paulo', 'SP', '01001-006');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Gisela Pinto', '777.777.777-77', '11977777777', 'tutor7@email.com', NULL, 'Rua G', '70', 'Bairro 7', 'São Paulo', 'SP', '01001-007');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Heitor Nunes', '888.888.888-88', '11988888888', 'tutor8@email.com', NULL, 'Rua H', '80', 'Bairro 8', 'São Paulo', 'SP', '01001-008');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('Isabela Mendes', '999.999.999-99', '11999999999', 'tutor9@email.com', NULL, 'Rua I', '90', 'Bairro 9', 'São Paulo', 'SP', '01001-009');
INSERT INTO tb_tutor (nome_completo, cpf, telefone, email, user_id, rua, numero, bairro, cidade, estado, cep)
VALUES ('João Pedro', '000.000.000-00', '11900000000', 'tutor10@email.com', NULL, 'Rua J', '100', 'Bairro 10', 'São Paulo', 'SP', '01001-010');

-- 4. tb_veterinario (10 registros)
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dr. Carlos Silva', 'vet1@clyvopaws.com', '11912345678', 'CRMV-SP 11111', (SELECT id FROM tb_user WHERE username = 'vet1@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'matriz@pawscare.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dra. Patricia Lima', 'vet2@clyvopaws.com', '11912345679', 'CRMV-SP 22222', (SELECT id FROM tb_user WHERE username = 'vet2@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'contato@vidaanimal.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dr. Marcos Santos', 'vet3@clyvopaws.com', '11912345680', 'CRMV-SP 33333', (SELECT id FROM tb_user WHERE username = 'vet3@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'morumbi@petcare.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dra. Juliana Costa', 'vet4@clyvopaws.com', '11912345681', 'CRMV-SP 44444', (SELECT id FROM tb_user WHERE username = 'vet4@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'paulistana@vet.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dr. Roberto Alves', 'vet5@clyvopaws.com', '11912345682', 'CRMV-SP 55555', (SELECT id FROM tb_user WHERE username = 'vet5@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'centro@amigospets.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dra. Fernanda Souza', 'vet6@clyvopaws.com', '11912345683', 'CRMV-SP 66666', NULL, (SELECT id FROM tb_clinica WHERE email = 'pinheiros@vet.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dr. Lucas Ribeiro', 'vet7@clyvopaws.com', '11912345684', 'CRMV-SP 77777', NULL, (SELECT id FROM tb_clinica WHERE email = 'contato@4patas.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dra. Camila Duarte', 'vet8@clyvopaws.com', '11912345685', 'CRMV-SP 88888', NULL, (SELECT id FROM tb_clinica WHERE email = 'jardins@petsaude.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dr. Rafael Mendes', 'vet9@clyvopaws.com', '11912345686', 'CRMV-SP 99999', NULL, (SELECT id FROM tb_clinica WHERE email = 'paulista@vet.com'));
INSERT INTO tb_veterinario (nome_completo, email, telefone, crmv, user_id, clinica_id)
VALUES ('Dra. Beatriz Lima', 'vet10@clyvopaws.com', '11912345687', 'CRMV-SP 10101', NULL, (SELECT id FROM tb_clinica WHERE email = 'contato@zoovet.com'));

-- 5. tb_agenda_disponivel (10 registros)
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-01 08:00:00', TIMESTAMP '2026-06-01 09:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet1@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-01 09:00:00', TIMESTAMP '2026-06-01 10:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet2@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-01 10:00:00', TIMESTAMP '2026-06-01 11:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet3@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-01 11:00:00', TIMESTAMP '2026-06-01 12:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet4@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-01 13:00:00', TIMESTAMP '2026-06-01 14:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet5@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-01 14:00:00', TIMESTAMP '2026-06-01 15:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet6@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-01 15:00:00', TIMESTAMP '2026-06-01 16:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet7@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-01 16:00:00', TIMESTAMP '2026-06-01 17:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet8@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-02 08:00:00', TIMESTAMP '2026-06-02 09:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet9@clyvopaws.com'));
INSERT INTO tb_agenda_disponivel (data_hora_inicio, data_hora_fim, disponivel, veterinario_id)
VALUES (TIMESTAMP '2026-06-02 09:00:00', TIMESTAMP '2026-06-02 10:00:00', 1, (SELECT id FROM tb_veterinario WHERE email = 'vet10@clyvopaws.com'));

-- 6. tb_pet (10 registros)
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Mel', 'CACHORRO', 'Poodle', 5.5, 'FEMEA', DATE '2022-01-15', 'Pet dócil', (SELECT id FROM tb_tutor WHERE email = 'tutor1@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Thor', 'CACHORRO', 'Pitbull', 25.0, 'MACHO', DATE '2020-05-20', 'Pet ativo', (SELECT id FROM tb_tutor WHERE email = 'tutor2@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Luna', 'GATO', 'Siamês', 3.2, 'FEMEA', DATE '2024-02-10', 'Arisca com estranhos', (SELECT id FROM tb_tutor WHERE email = 'tutor3@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Bob', 'CACHORRO', 'Golden', 30.5, 'MACHO', DATE '2021-11-05', 'Muito brincalhão', (SELECT id FROM tb_tutor WHERE email = 'tutor4@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Nina', 'GATO', 'Persa', 4.0, 'FEMEA', DATE '2025-01-10', 'Tranquila', (SELECT id FROM tb_tutor WHERE email = 'tutor5@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Zeus', 'CACHORRO', 'Husky', 22.0, 'MACHO', DATE '2022-08-30', 'Uiva bastante', (SELECT id FROM tb_tutor WHERE email = 'tutor6@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Mia', 'GATO', 'Angorá', 3.8, 'FEMEA', DATE '2023-12-01', 'Gosta de colo', (SELECT id FROM tb_tutor WHERE email = 'tutor7@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Max', 'CACHORRO', 'Labrador', 28.0, 'MACHO', DATE '2019-07-14', 'Gosta de água', (SELECT id FROM tb_tutor WHERE email = 'tutor8@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Pantera', 'GATO', 'Maine Coon', 8.5, 'MACHO', DATE '2021-04-22', 'Gigante gentil', (SELECT id FROM tb_tutor WHERE email = 'tutor9@email.com'));
INSERT INTO tb_pet (nome, especie, raca, peso, sexo, data_nascimento, descricao, tutor_id)
VALUES ('Pipoca', 'CACHORRO', 'Shih Tzu', 6.0, 'FEMEA', DATE '2024-09-09', 'Ligeiramente surda', (SELECT id FROM tb_tutor WHERE email = 'tutor10@email.com'));

-- 7. tb_catalogo_preventivo (10 registros)
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('CACHORRO', 'Poodle', 'Catarata', 84, 'Exame anual', 'Dieta com antioxidantes');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('CACHORRO', 'Pitbull', 'Displasia', 60, 'Raio-x de quadril', 'Evitar pisos lisos');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('GATO', 'Siamês', 'Asma Felina', 48, 'Raio-x torácico', 'Evitar poeira');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('CACHORRO', 'Golden', 'Obesidade', 36, 'Controle de peso', 'Exercícios diários');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('GATO', 'Persa', 'Rim policístico', 24, 'Ultrassom anual', 'Incentivar hidratação');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('CACHORRO', 'Husky', 'Glaucoma', 72, 'Tonometria', 'Proteção solar');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('GATO', 'Angorá', 'Surdez', 12, 'Teste auditivo', 'Adaptação de sinais');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('CACHORRO', 'Labrador', 'Displasia', 48, 'Controle de peso', 'Condroprotetores');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('GATO', 'Maine Coon', 'Cardiomiopatia', 60, 'Ecocardiograma', 'Monitoramento cardíaco');
INSERT INTO tb_catalogo_preventivo (especie, raca, doenca_predisposta, idade_alerta_meses, dica_prevencao, cuidados_recomendados)
VALUES ('CACHORRO', 'Shih Tzu', 'Úlcera de córnea', 24, 'Higiene ocular', 'Cuidado com pelos no olho');

-- 8. tb_consulta (10 registros)
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-01 08:30:00', 'Rotina', 'Saudável', (SELECT id FROM tb_pet WHERE nome = 'Mel'), (SELECT id FROM tb_veterinario WHERE email = 'vet1@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'matriz@pawscare.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-01 09:30:00', 'Coceira', 'Alergia', (SELECT id FROM tb_pet WHERE nome = 'Thor'), (SELECT id FROM tb_veterinario WHERE email = 'vet2@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'contato@vidaanimal.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-01 10:30:00', 'Apatia', 'Virose', (SELECT id FROM tb_pet WHERE nome = 'Luna'), (SELECT id FROM tb_veterinario WHERE email = 'vet3@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'morumbi@petcare.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-01 11:30:00', 'Dor', 'Artrose', (SELECT id FROM tb_pet WHERE nome = 'Bob'), (SELECT id FROM tb_veterinario WHERE email = 'vet4@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'paulistana@vet.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-01 13:30:00', 'Vômito', 'Gastrite', (SELECT id FROM tb_pet WHERE nome = 'Nina'), (SELECT id FROM tb_veterinario WHERE email = 'vet5@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'centro@amigospets.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-01 14:30:00', 'Checkup', 'Ok', (SELECT id FROM tb_pet WHERE nome = 'Zeus'), (SELECT id FROM tb_veterinario WHERE email = 'vet6@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'pinheiros@vet.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-01 15:30:00', 'Olho vermelho', 'Conjuntivite', (SELECT id FROM tb_pet WHERE nome = 'Mia'), (SELECT id FROM tb_veterinario WHERE email = 'vet7@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'contato@4patas.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-01 16:30:00', 'Falta de apetite', 'Verme', (SELECT id FROM tb_pet WHERE nome = 'Max'), (SELECT id FROM tb_veterinario WHERE email = 'vet8@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'jardins@petsaude.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-02 08:30:00', 'Tosse', 'Gripe', (SELECT id FROM tb_pet WHERE nome = 'Pantera'), (SELECT id FROM tb_veterinario WHERE email = 'vet9@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'paulista@vet.com'));
INSERT INTO tb_consulta (data_hora, resumo, diagnostico, pet_id, veterinario_id, clinica_id)
VALUES (TIMESTAMP '2026-06-02 09:30:00', 'Revisão', 'Saudável', (SELECT id FROM tb_pet WHERE nome = 'Pipoca'), (SELECT id FROM tb_veterinario WHERE email = 'vet10@clyvopaws.com'), (SELECT id FROM tb_clinica WHERE email = 'contato@zoovet.com'));

-- 9. tb_agendamento (10 registros)
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-01 08:30:00', 'Rotina Mel', 'Retorno', (SELECT id FROM tb_consulta WHERE diagnostico = 'Saudável' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-01 09:30:00', 'Dermatologista Thor', 'Pele', (SELECT id FROM tb_consulta WHERE diagnostico = 'Alergia' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-01 10:30:00', 'Exame Luna', 'Apatia', (SELECT id FROM tb_consulta WHERE diagnostico = 'Virose' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-01 11:30:00', 'Ortopedia Bob', 'Pata', (SELECT id FROM tb_consulta WHERE diagnostico = 'Artrose' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-01 13:30:00', 'Gastro Nina', 'Vômito', (SELECT id FROM tb_consulta WHERE diagnostico = 'Gastrite' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-01 14:30:00', 'Check-up Zeus', 'Anual', (SELECT id FROM tb_consulta WHERE diagnostico = 'Ok' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-01 15:30:00', 'Oftalmo Mia', 'Olho', (SELECT id FROM tb_consulta WHERE diagnostico = 'Conjuntivite' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-01 16:30:00', 'Vermifugação Max', 'Remédio', (SELECT id FROM tb_consulta WHERE diagnostico = 'Verme' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-02 08:30:00', 'Pneumo Pantera', 'Tosse', (SELECT id FROM tb_consulta WHERE diagnostico = 'Gripe' AND ROWNUM = 1));
INSERT INTO tb_agendamento (data_hora, titulo, descricao, consulta_id)
VALUES (TIMESTAMP '2026-06-02 09:30:00', 'Revisão Pipoca', 'Geral', (SELECT id FROM tb_consulta WHERE diagnostico = 'Saudável' AND ROWNUM = 1));

-- 10. tb_medicamento (10 registros)
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Vitamina A', '1 comp', '1x ao dia', DATE '2026-06-01', 30, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Saudável' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Pomada X', 'Local', '2x ao dia', DATE '2026-06-01', 7, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Alergia' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Antibiótico', '5ml', '12/12h', DATE '2026-06-01', 10, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Virose' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Anti-inflamatório', '1 comp', '24/24h', DATE '2026-06-01', 5, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Artrose' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Protetor', '1 comp', '1x ao dia', DATE '2026-06-01', 14, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Gastrite' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Vitamina C', '1 comp', '1x ao dia', DATE '2026-06-01', 30, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Ok' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Colírio', '2 gotas', '8/8h', DATE '2026-06-01', 5, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Conjuntivite' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Vermífugo', '1 comp', 'Única', DATE '2026-06-01', 1, 'CONCLUIDO', (SELECT id FROM tb_consulta WHERE diagnostico = 'Verme' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Xarope', '3ml', '8/8h', DATE '2026-06-02', 7, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Gripe' AND ROWNUM = 1));
INSERT INTO tb_medicamento (nome, dosagem, frequencia, data_inicio, duracao_dias, status, consulta_id)
VALUES ('Suplemento', '1 comp', '1x ao dia', DATE '2026-06-02', 60, 'EM_DIA', (SELECT id FROM tb_consulta WHERE diagnostico = 'Saudável' AND ROWNUM = 1));

-- 11. tb_historico_dose (10 registros)
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-01 09:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Vitamina A' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-01 10:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Pomada X' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-01 11:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Antibiótico' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-01 12:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Anti-inflamatório' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-01 14:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Protetor' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-01 15:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Vitamina C' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-01 16:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Colírio' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-01 17:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Vermífugo' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-02 09:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Xarope' AND ROWNUM = 1));
INSERT INTO tb_historico_dose (data_hora_toma, medicamento_id)
VALUES (TIMESTAMP '2026-06-02 10:00:00', (SELECT id FROM tb_medicamento WHERE nome = 'Suplemento' AND ROWNUM = 1));

COMMIT;