-- Usuário administrativo (único perfil autorizado a cadastrar veterinários).
-- Não existe endpoint público para criar ADMIN (por design de segurança);
-- por isso o primeiro admin é semeado via Flyway.
--
-- ATENÇÃO: substitua o hash abaixo por um hash bcrypt real antes de rodar a
-- migration. Gere com br.com.fiap.clyvopaws.tools.GerarHashesSeed (rode a
-- main() localmente e copie o hash impresso para "Senha para ADMIN").
-- Login de teste sugerido: admin@clyvopaws.com / Admin@123
INSERT INTO tb_user (username, password, role)
VALUES ('admin@clyvopaws.com', 'SUBSTITUA_PELO_HASH_BCRYPT_GERADO', 'ADMIN');
