
Readme · MD
# CLYVO PAWS (CLYVO VET) - FIAP CHALLENGE 2026

API RESTful desenvolvida para o sistema de gerenciamento veterinario Clyvo Paws.
Este projeto visa digitalizar e otimizar o atendimento clínico, histórico médico
e acompanhamento preventivo de pets.
 
--------------------------------------------------------------------------------
## 👥 INTEGRANTES DO GRUPO (Turma: 2TDSPX)
--------------------------------------------------------------------------------
* Felipe Ribeiro Salles de Camargo | RM: 565224
* João Pedro Pereira Camilo        | RM: 562005
* Lucas Matsubara Reis             | RM: 565020
* Pamella Christiny Chaves Brito   | RM: 565206
--------------------------------------------------------------------------------
## 🚀 ATENDIMENTO AOS REQUISITOS (JAVA ADVANCED)
--------------------------------------------------------------------------------
Para facilitar a correção pelo professor, abaixo estão os pontos chave exigidos
no escopo do Challenge:

### Spring Security (autenticação e autorização)
1. Autenticação stateless via JWT, assinado com par de chaves RSA (Spring
   Security OAuth2 Resource Server + Nimbus JOSE+JWT).
2. Três perfis de usuário com permissões diferentes: TUTOR, VETERINARIO e
   ADMIN — ver tabela completa em "Regras de Autorização" abaixo.
3. Proteção de rotas por perfil (`hasRole`) **e** por posse do recurso (ex:
   um tutor só acessa/edita os próprios pets, consultas e agendamentos; um
   veterinário só vê/gerencia as próprias consultas), reforçada dentro dos
   Services via `AuthorizationService`.
### Flyway (controle de versão do banco)
4. Migrations versionadas (`V1`, `V2`, `V3`) com criação de estrutura, carga
   inicial de dados de teste e seed do usuário administrativo.
### Funcionalidades completas (fluxos além de simples CRUD)
5. Fluxo de agendamento: consulta de horários livres do veterinário,
   checagem de sobreposição de horário e reserva do slot.
6. Fluxo de acompanhamento de medicação: registro de cada dose tomada pelo
   pet, com validação de que o registro não pode ser feito com data futura.
### Demais boas práticas
7. Validação de campos (Bean Validation): implementado via DTOs (`@NotBlank`,
   `@Size` alinhado aos limites reais das colunas no banco, `@Email`,
   `@Pattern` para CPF/CNPJ/CEP, `@Positive`, `@PastOrPresent`, `@Future`),
   incluindo validação em cascata de objetos aninhados (`@Valid`).
8. Paginação e Ordenação: implementado via `@PageableDefault` nos endpoints
   de listagem (Tutores, Pets, Veterinários, Consultas, Medicamentos,
   Agendamentos, Clínicas).
9. Busca com parâmetros: implementado na rota `GET /planos-preventivos?especie=CACHORRO`.
10. Uso de Cache: implementado com `@EnableCaching` e `@Cacheable` na Service
    de CatalogoPreventivo e de Consulta.
11. Tratamento de Exceções: implementado via `@RestControllerAdvice`
    (`GlobalExceptionHandler`), mapeando erros de validação e regra de
    negócio (400), autenticação/autorização (401/403), recurso não
    encontrado (404) e parâmetro de ordenação inválido (400), além de um
    handler genérico para qualquer falha não mapeada (500).
12. Relacionamentos JPA e DTOs: implementado Data Shaping (Slim Payloads)
    para otimizar as consultas evitando loops infinitos, com cascata de
    exclusão configurada corretamente em toda a árvore de relacionamentos
    (Tutor → Pet → Consulta → Medicamento/Agendamento) para evitar erro de
    violação de chave estrangeira ao excluir um registro pai.
--------------------------------------------------------------------------------
## 🛡️ REGRAS DE AUTORIZAÇÃO (quem pode fazer o quê)
--------------------------------------------------------------------------------
Além do perfil (TUTOR / VETERINARIO / ADMIN) checado nas rotas, o
`AuthorizationService` (pacote `auth`) reforça a posse do registro dentro
dos Services de domínio:

| Recurso                        | Regra |
|---------------------------------|-------|
| Tutor (perfil)                  | Tutor só vê/edita/exclui o próprio; VETERINARIO/ADMIN veem qualquer um |
| Pet                             | Tutor só cria/vê/edita/exclui os próprios pets; VETERINARIO/ADMIN veem todos |
| Consulta                        | VETERINARIO só vê/gerencia as consultas em que é o responsável; tutor só vê consultas dos próprios pets (somente leitura); ADMIN acesso total |
| Medicamento / registro de dose  | Mesma regra da consulta de origem; tutor pode registrar dose tomada nos próprios pets |
| Agendamento                     | Tutor só cria/vê/edita/exclui agendamentos ligados às consultas dos próprios pets |
| AgendaDisponivel (horários livres do vet) | Criar/excluir: só o próprio veterinário. Ver horários livres: qualquer autenticado (tutor navegando pra agendar, ou o próprio vet) |
| Veterinario (perfil)            | Cadastro: só ADMIN. Listagem/detalhe: qualquer autenticado. Editar/excluir: só o próprio vet ou ADMIN |
| Clinica / CatalogoPreventivo    | Leitura: qualquer autenticado. Escrita (POST/PUT/DELETE): só ADMIN |

Tentativas fora dessas regras retornam **403 Forbidden**.
 
--------------------------------------------------------------------------------
## 📁 ONDE ENCONTRAR OS ARTEFATOS EXIGIDOS
--------------------------------------------------------------------------------
Todos os documentos probatorios estao na pasta raiz do projeto na pasta "/documentos":

* Cronograma de Desenvolvimento: Veja o arquivo `cronograma.pdf`.
* Diagramas (Classes, DER e Arquitetura): Veja a pasta `/documentos/diagramas`.
* Collection do Postman/Insomnia: Veja o arquivo `clyvopaws_collection.json` na raiz do projeto contendo as requisicoes exportadas.
--------------------------------------------------------------------------------
## 🔐 CONFIGURAÇÃO DE SEGURANÇA (OBRIGATÓRIO ANTES DE RODAR)
--------------------------------------------------------------------------------
A API usa JWT assinado com par de chaves RSA. As chaves NÃO ficam versionadas
no Git (`.gitignore`) e precisam ser geradas localmente antes do primeiro run:

1. Gere o par de chaves (a partir da raiz do projeto):
```
   mkdir -p src/main/resources/keys
   openssl genpkey -algorithm RSA -pkeyopt rsa_keygen_bits:2048 -out src/main/resources/keys/private_key.pem
   openssl rsa -pubout -in src/main/resources/keys/private_key.pem -out src/main/resources/keys/public_key.pem
```

2. Os hashes de senha do seed (Flyway `V2`/`V3`) precisam ser reais (bcrypt).
   Rode a classe utilitária `br.com.fiap.clyvopaws.tools.GerarHashesSeed` (método
   `main`), copie os hashes impressos no console e substitua o texto
   `SUBSTITUA_PELO_HASH_BCRYPT_GERADO` nos arquivos `V2__populate_initial_data_log.sql`
   e `V3__seed_admin_user.sql`. Depois de substituir, essa classe pode ser apagada.
   ⚠️ Faça esse passo **antes** de rodar o projeto pela primeira vez. Se você
   editar uma migration do Flyway depois que ela já foi aplicada num banco, a
   próxima subida falha com erro de checksum ("Migration checksum mismatch").
   Se isso acontecer, apague as tabelas e a `flyway_schema_history` do seu
   schema e suba de novo.
3. Usuários de teste após o seed:
    * Tutores/Veterinários: `<username do seed, ex. tutor1@email.com>` / `Senha@123`
    * Admin (único perfil que pode cadastrar veterinário via `POST /veterinarios`):
      `admin@clyvopaws.com` / `Admin@123`
--------------------------------------------------------------------------------
## ⚙️ COMO EXECUTAR O PROJETO LOCALMENTE
--------------------------------------------------------------------------------
1. Configure suas credenciais do Banco de Dados Oracle no arquivo `application.properties`.
2. Gere as chaves RSA e os hashes de senha (seção "Configuração de Segurança" acima).
3. Execute o projeto via Maven: `mvn spring-boot:run` ou pela sua IDE.
4. Acesse a documentação do Swagger: http://localhost:8080/swagger-ui.html
--------------------------------------------------------------------------------
## 🔑 AUTENTICAÇÃO: COMO USAR O TOKEN JWT
--------------------------------------------------------------------------------
Faça `POST /login` com `{"username": "...", "password": "..."}` — a resposta
traz `{"token": "..."}`. Esse token precisa ir no header
`Authorization: Bearer <token>` em toda rota protegida.

Depois de logado, use `GET /tutores/me` ou `GET /veterinarios/me` para
descobrir o próprio ID (necessário pra chamar as rotas que exigem posse,
como `GET /pets/tutor/{tutorId}`).

**No Swagger UI:**
1. Faça `POST /login` pelo "Try it out" e copie o valor de `token` da resposta.
2. Clique no botão verde "Authorize" (cadeado) no topo da página.
3. Cole o token puro no campo `bearerAuth` (sem o prefixo "Bearer", o Swagger
   adiciona sozinho) e clique em "Authorize".
4. Toda chamada feita pelo "Try it out" a partir daí já sai autenticada.
   **No Postman:**
1. Faça o `POST /login` numa requisição normal e copie o `token` da resposta.
2. Na requisição que quer autenticar, aba "Authorization" → Type "Bearer Token"
   → cole o token puro no campo "Token".
3. (Opcional) Automatize com uma variável de collection: no `POST /login`, aba
   "Tests", adicione `pm.collectionVariables.set('token', pm.response.json().token);`
   e use `{{token}}` como Bearer Token nas demais requisições.
--------------------------------------------------------------------------------
## 📱 CONECTANDO COM O APP MOBILE (React Native / Expo)
--------------------------------------------------------------------------------