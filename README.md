# CLYVO PAWS (CLYVO VET) - FIAP CHALLENGE 2026

API RESTful desenvolvida para o sistema de gerenciamento veterinário Clyvo Paws.
Este projeto visa digitalizar e otimizar o atendimento clínico, histórico médico
e acompanhamento preventivo de pets, servindo de backend para o app mobile
(React Native/Expo) desenvolvido em paralelo pela mesma equipe.

--------------------------------------------------------------------------------
## 👥 INTEGRANTES DO GRUPO (Turma: 2TDSPX)
--------------------------------------------------------------------------------
* Felipe Ribeiro Salles de Camargo | RM: 565224
* João Pedro Pereira Camilo        | RM: 562005
* Lucas Matsubara Reis             | RM: 565020
* Pamella Christiny Chaves Brito   | RM: 565206

--------------------------------------------------------------------------------
## 🧱 STACK TÉCNICA
--------------------------------------------------------------------------------
* **Linguagem**: Java 21
* **Framework**: Spring Boot 3.2.6
* **Persistência**: Spring Data JPA + Hibernate, banco Oracle
* **Migrations**: Flyway (`flyway-core` + `flyway-database-oracle`)
* **Segurança**: Spring Security + OAuth2 Resource Server, JWT assinado com
  par de chaves RSA (Nimbus JOSE+JWT)
* **Validação**: Bean Validation (Jakarta Validation / Hibernate Validator)
* **Documentação**: springdoc-openapi (Swagger UI)
* **Build**: Maven
* **Utilitários**: Lombok

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
5. Fluxo de agendamento de consulta: o tutor escolhe pet, clínica, veterinário
   e horário; `POST /agendamentos` aceita tanto reaproveitar uma Consulta já
   existente (`consultaId`) quanto criar a Consulta na hora a partir de
   `petId` + `clinicaId` + `veterinarioId`, checando sobreposição de horário
   na agenda do veterinário antes de reservar.
6. Fluxo de acompanhamento de medicação: registro de cada dose tomada pelo
   pet (`POST /medicamentos/doses/check`), com validação de que o registro
   não pode ser feito com data futura.

### Demais boas práticas
7. Validação de campos (Bean Validation): implementado via DTOs (`@NotBlank`,
   `@Size` alinhado aos limites reais das colunas no banco, `@Email`,
   `@Pattern` para CPF/CNPJ/CEP, `@Positive`, `@PastOrPresent`, `@Future`),
   incluindo validação em cascata de objetos aninhados (`@Valid`).
8. Paginação e Ordenação: implementado via `@PageableDefault` nos endpoints
   de listagem (Tutores, Pets, Veterinários, Consultas, Medicamentos,
   Agendamentos, Clínicas, Catálogo Preventivo).
9. Busca com parâmetros: implementado em `GET /planos-preventivos/busca?especie=CACHORRO`.
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
| Medicamento / registro de dose  | Tutor vê e registra dose só nos próprios pets; veterinário só nas consultas em que é responsável; ADMIN acesso total |
| Agendamento                     | Tutor só cria/vê/edita/exclui agendamentos ligados aos próprios pets |
| AgendaDisponivel (horários livres do vet) | Criar/excluir: só o próprio veterinário. Ver horários livres: qualquer autenticado (tutor navegando pra agendar, ou o próprio vet) |
| Veterinario (perfil)            | Cadastro: só ADMIN. Listagem/detalhe: qualquer autenticado. Editar/excluir: só o próprio vet ou ADMIN |
| Clinica / CatalogoPreventivo    | Leitura: qualquer autenticado. Escrita (POST/PUT/DELETE): só ADMIN |

Tentativas fora dessas regras retornam **403 Forbidden**.

--------------------------------------------------------------------------------
## 🌐 PRINCIPAIS ENDPOINTS
--------------------------------------------------------------------------------
A lista completa e interativa está no Swagger (veja a seção de execução
abaixo). Resumo dos grupos de rotas:

| Grupo | Endpoints principais |
|---|---|
| Autenticação | `POST /login` |
| Tutores | `POST /tutores` (público), `GET/PUT/DELETE /tutores/{id}`, `GET /tutores` |
| Pets | `POST /pets`, `GET/PUT/DELETE /pets/{id}`, `GET /pets/tutor/{tutorId}`, `GET /pets` |
| Veterinários | `POST /veterinarios` (só ADMIN), `GET/PUT/DELETE /veterinarios/{id}`, `GET /veterinarios` |
| Clínicas | `GET /clinicas`, `GET /clinicas/{id}`, `POST/PUT/DELETE /clinicas/{id}` (só ADMIN) |
| Consultas | `POST /consultas`, `GET/PUT/DELETE /consultas/{id}`, `GET /consultas/pet/{petId}`, `GET /consultas` |
| Medicamentos | `POST /medicamentos`, `GET/PUT/DELETE /medicamentos/{id}`, `GET /medicamentos/consulta/{consultaId}`, `POST /medicamentos/doses/check`, `GET /medicamentos` |
| Agendamentos | `POST /agendamentos` (com ou sem `consultaId` — sem ele, cria a consulta na hora), `GET/PUT/DELETE /agendamentos/{id}`, `GET /agendamentos/consulta/{consultaId}`, `GET /agendamentos/pet/{petId}`, `GET /agendamentos/tutor/{tutorId}` |
| Agenda do veterinário | `POST /agendas` (só o próprio vet), `GET /agendas/veterinario/{veterinarioId}`, `DELETE /agendas/{id}` |
| Catálogo preventivo | `GET /planos-preventivos`, `GET /planos-preventivos/busca?especie=CACHORRO`, `POST/PUT/DELETE /planos-preventivos/{id}` (só ADMIN) |

--------------------------------------------------------------------------------
## ⚠️ LIMITAÇÕES CONHECIDAS
--------------------------------------------------------------------------------
* `PUT /tutores/{id}` (via `TutorUpdateDTO`) atualiza nome, e-mail, CPF,
  telefone e foto, mas **não atualiza o endereço** do tutor. Essa decisão foi 
  implementada pois na aplicação é solicitado do tutor que dê permissão de sua localização atual.
* Ao alterar o e-mail de um tutor, o `username` de login (`tb_user`) é
  sincronizado automaticamente — mas o token JWT já emitido continua válido
  com o e-mail **antigo** como identidade até expirar. Depois de trocar o
  e-mail, é necessário fazer login novamente para obter um token atualizado.
* Somente o login de **TUTOR** devolve o próprio perfil (`nomeCompleto`,
  `fotoUrl`, `id`) direto na resposta de `POST /login`. Um VETERINARIO ou
  ADMIN autenticado recebe só o `token`; ainda não há endpoint dedicado pra esses
  perfis descobrirem o próprio ID depois do login.


--------------------------------------------------------------------------------
## ⚙️ COMO EXECUTAR O PROJETO LOCALMENTE (PASSO A PASSO)
--------------------------------------------------------------------------------

### Pré-requisitos
* JDK 21
* Maven (ou use o `mvnw`/`mvnw.cmd` incluso no projeto)
* Acesso a um banco Oracle (ex: o Oracle da FIAP)
* OpenSSL (pra gerar as chaves RSA — já vem instalado em Linux/Mac; no
  Windows, costuma vir junto com o Git Bash, ou pode instalar separado)

### Passo 1 — Clonar e configurar o banco
1. Clone o repositório e abra na sua IDE.
2. Em `src/main/resources/application.properties`, configure `spring.datasource.url`,
   `spring.datasource.username` e `spring.datasource.password` com suas
   credenciais Oracle.

### Passo 2 — Gerar o par de chaves RSA (obrigatório)
A API usa JWT assinado com RSA. As chaves NÃO ficam versionadas no Git
(`.gitignore`) — cada máquina/clone precisa gerar o próprio par antes do
primeiro run:
```
mkdir -p src/main/resources/keys
openssl genpkey -algorithm RSA -pkeyopt rsa_keygen_bits:2048 -out src/main/resources/keys/private_key.pem
openssl rsa -pubout -in src/main/resources/keys/private_key.pem -out src/main/resources/keys/public_key.pem
```
Sem isso, a aplicação falha ao subir (bean do `JwtDecoder`/`JwtEncoder` não
consegue ser criado).

### Passo 3 — Subir a aplicação
Os dados de teste (`V1`, `V2`, `V3`) já vêm com hashes de senha reais
prontos — não é preciso gerar nada além das chaves do Passo 2. Só rodar:
```
mvn spring-boot:run
```
Ou execute a classe `ClyvovetApplication` direto pela IDE. O Flyway aplica
as migrations automaticamente e o servidor sobe em `http://localhost:8080`.

⚠️ Se em algum momento você editar uma migration do Flyway (`V1`/`V2`/`V3`)
depois que ela já foi aplicada num banco, a próxima subida quebra com erro
de checksum ("Migration checksum mismatch"). Se isso acontecer, apague as
tabelas e a `flyway_schema_history` do seu schema e suba de novo.

### Passo 4 — Conferir que subiu
Acesse `http://localhost:8080/swagger-ui.html` — deve listar todos os
endpoints, com um botão verde "Authorize" no topo da página.

### Usuários de teste (dados do seed)
* Tutores/Veterinários: `<username do seed, ex. tutor1@email.com>` / `Senha@123`
* Admin (único perfil que pode cadastrar veterinário via `POST /veterinarios`):
  `admin@clyvopaws.com` / `Admin@123`

--------------------------------------------------------------------------------
## 🔑 AUTENTICAÇÃO: COMO USAR O TOKEN JWT
--------------------------------------------------------------------------------
Faça `POST /login` com `{"username": "...", "password": "..."}`. A resposta
traz:
```json
{
  "token": "eyJhbGciOiJSUzI1NiJ9...",
  "nomeCompleto": "Ana Souza",
  "fotoUrl": "https://...",
  "id": 3
}
```
`nomeCompleto`, `fotoUrl` e `id` só vêm preenchidos quando quem faz login é
um **TUTOR** (é o próprio ID do tutor, útil pra montar as demais chamadas
como `GET /pets/tutor/{tutorId}` sem precisar de outra requisição). Pra
VETERINARIO/ADMIN, esses campos vêm nulos ou com o `username` no lugar do
nome — só o `token` é garantido pra qualquer perfil.

O token vai no header `Authorization: Bearer <token>` em toda rota protegida.

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
## 📱 CONECTANDO COM O APP MOBILE (React Native / Expo Go)
--------------------------------------------------------------------------------
O CORS (`CorsConfig.java`) está liberado para `http://localhost:8081` nos
métodos GET/POST/PUT/DELETE/PATCH/OPTIONS. Isso só importa se o app for
testado via **Expo Web** (navegador) — testando via **Expo Go** nativo
(celular físico ou emulador), CORS não se aplica, então não é preciso mexer
nisso.

Testando via **Expo Go num celular físico**, na mesma rede Wi-Fi do PC onde
a API roda:

1. `localhost` no celular aponta pro próprio celular, não pro PC. No código
   do app RN, use o IP local do PC (Windows: `ipconfig` → endereço IPv4 do
   adaptador Wi-Fi, ex: `http://192.168.0.105:8080`), nunca `localhost:8080`.
2. O Firewall do Windows pode bloquear a conexão vinda do celular na porta
   8080 — libere o Java (ou a porta) pra redes "Privada" em Firewall do
   Windows Defender → Permitir um aplicativo.
3. Teste rápido pra isolar o problema: abra
   `http://<IP-DO-PC>:8080/swagger-ui.html` no navegador do próprio celular.
   Se abrir, a rede está OK e qualquer erro daí em diante é no app, não na
   conexão.

Testando via **emulador Android**: use `http://10.0.2.2:8080` (alias
especial que o emulador usa pra apontar pro `localhost` do host).

Testando via **Expo Web** (`expo start --web`): ajuste `CorsConfig.java`
se a porta do dev server do Expo Web não for `8081`, ou adicione mais de
uma origem permitida.

--------------------------------------------------------------------------------