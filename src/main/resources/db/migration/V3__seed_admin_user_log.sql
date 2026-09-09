-- Usuário administrativo (único perfil autorizado a cadastrar veterinários).
-- Não existe endpoint público para criar ADMIN (por design de segurança);
-- por isso o primeiro admin é semeado via Flyway.

INSERT INTO tb_user (username, password, role)
VALUES ('admin@clyvopaws.com', '$2a$10$0yyCGpp8/fv9QzpZIjTd3.dBTZpLc3gfEVzEY/jVB0t1Hyno5s8bG', 'ADMIN');
