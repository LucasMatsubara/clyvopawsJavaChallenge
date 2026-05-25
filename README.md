# CLYVO PAWS (CLYVO VET) - FIAP CHALLENGE 2026

API RESTful desenvolvida para o sistema de gerenciamento veterinario Clyvo Paws. 
Este projeto visa digitalizar e otimizar o atendimento clínico, histórico médico 
e acompanhamento preventivo de pets.

--------------------------------------------------------------------------------
## 👥 INTEGRANTES DO GRUPO (Turma: 2TDSPX)
--------------------------------------------------------------------------------
* João Pedro Pereira Camilo       | RM: 562005
* Lucas Matsubara Reis            | RM: 565020
* Pamella Christiny Chaves Brito  | RM: 565206

--------------------------------------------------------------------------------
## 🚀 ATENDIMENTO AOS REQUISITOS (JAVA ADVANCED)
--------------------------------------------------------------------------------
Para facilitar a correção pelo professor, abaixo estao os pontos chave exigidos 
no escopo do Challenge:

1. Validação de campos (Bean Validation): Implementado via DTOs (@NotBlank, @Size, @Email).
2. Paginação e Ordenação: Implementado via @PageableDefault nos endpoints de Consultas, Medicamentos e Pets.
3. Busca com parâmetros: Implementado na rota GET /planos-preventivos?especie=CACHORRO.
4. Uso de Cache: Implementado com @EnableCaching e @Cacheable na Service de CatalogoPreventivo e de Consulta.
5. Tratamento de Exceções: Implementado via @RestControllerAdvice (GlobalExceptionHandler) mapeando erros 400 e 404.
6. Relacionamentos JPA e DTOs: Implementado Data Shaping (Slim Payloads) para otimizar as consultas evitando loops infinitos.

--------------------------------------------------------------------------------
## 📁 ONDE ENCONTRAR OS ARTEFATOS EXIGIDOS
--------------------------------------------------------------------------------
Todos os documentos probatorios estao na pasta raiz do projeto na pasta "/documentos":

* Cronograma de Desenvolvimento: Veja o arquivo `cronograma.pdf`.
* Diagramas (Classes, DER e Arquitetura): Veja a pasta `/documentos/diagramas`.
* Collection do Postman/Insomnia: Veja o arquivo `clyvopaws_collection.json` na raiz do projeto contendo as requisicoes exportadas.

--------------------------------------------------------------------------------
## ⚙️ COMO EXECUTAR O PROJETO LOCALMENTE
--------------------------------------------------------------------------------
1. Configure suas credenciais do Banco de Dados Oracle no arquivo 'application.properties'.
2. Execute o projeto via Maven: 'mvn spring-boot:run' ou pela sua IDE.
3. Acesse a documentação do Swagger: http://localhost:8080/swagger-ui.html

--------------------------------------------------------------------------------






--------------------------------------------------------------------------------

# Clyvo Paws API - Infraestrutura e DevOps

## 1. Descrição do Projeto
A **Clyvo Paws API** é o coração de um ecossistema veterinário focado na continuidade do cuidado animal. Trata-se de uma API RESTful desenvolvida em Java (Spring Boot) conteinerizada, projetada para gerenciar prontuários, tutores, agendamentos e registrar check-ins diários de medicamentos. A solução roda de forma automatizada na nuvem através do Microsoft Azure e Docker.

## 2. Benefícios para o Negócio
- **Combate ao Abandono Terapêutico:** Viabiliza alertas e acompanhamento da saúde do pet pós-consulta.
- **Aumento do LTV (Life Time Value):** Fideliza tutores à clínica veterinária com engajamento constante e agendamentos automatizados de retorno.
- **Escalabilidade e Redução de Custos:** A arquitetura em containers (Docker) hospedada no Azure permite escalar a infraestrutura sob demanda com baixo custo operacional.

## 3. Desenho Macro da Arquitetura
*(Nota: A imagem detalhada da arquitetura Macro encontra-se no PDF de entrega final da equipe).*
- **Atores:** Tutores e Clínicas Veterinárias interagindo via App.
- **Cloud Provider (Microsoft Azure):** Máquina Virtual Linux provisionada para hospedar a aplicação.
- **Engine (Docker):** Orquestra dois containers principais.
  - `Container 1`: Spring Boot API exposta na porta 8080.
  - `Container 2`: Banco de Dados Relacional Oracle com **Volume Nomeado** para garantir a persistência dos dados.

## 4. Rotas da API (Endpoints Principais)
A aplicação possui operações completas de CRUD. Algumas das principais rotas são:
- **Tutores:** POST /tutores, GET /tutores, GET /tutores/{id}
- **Catálogo Preventivo:** POST /planos-preventivos, GET /planos-preventivos/busca?especie={especie}
- **Consultas e Histórico:** GET /consultas/pet/{petId}, POST /consultas
- **Medicamentos e Check-in:** POST /medicamentos/doses/check (Registra que o tutor administrou o remédio).

## 5. Instalação da Solução (How To)
Passo a passo para rodar a aplicação em um ambiente Linux limpo sem privilégios de root:

1. Atualize o sistema e instale as dependências:
   sudo apt-get update && sudo apt-get upgrade -y
   sudo apt-get install git nano docker.io docker-compose -y

2. Dê permissão ao usuário atual para rodar o Docker (sem sudo):
   sudo usermod -aG docker $USER
   newgrp docker

3. Clone este repositório e acesse a pasta:
   git clone https://github.com/LucasMatsubara/clyvopawsJavaChallenge.git
   cd clyvopawsJavaChallenge

4. Suba os containers em background:
   docker-compose up -d

5. Teste a aplicação acessando via navegador ou Postman:
   http://<IP_DA_MAQUINA>:8080/tutores

## 6. Scripts de Infraestrutura e Automação

### 6.1. Script Azure CLI (Provisionamento da Nuvem)
#!/bin/bash
# Criação do Grupo de Recursos
az group create --name ClyvoPawsRG --location eastus

# Provisionamento da Máquina Virtual Linux (Ubuntu)
az vm create \
  --resource-group ClyvoPawsRG \
  --name ClyvoPawsVM \
  --image Ubuntu2204 \
  --admin-username azureuser \
  --generate-ssh-keys

# Abertura da porta 8080 para a API Spring Boot
az vm open-port --port 8080 --resource-group ClyvoPawsRG --name ClyvoPawsVM


### 6.2. Dockerfile (Aplicação Spring Boot)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests



FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]



RUN addgroup -S clyvogroup && adduser -S clyvouser -G clyvogroup
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN chown clyvouser:clyvogroup app.jar
USER clyvouser
ENTRYPOINT ["java", "-jar", "app.jar"]


### 6.3. YAML do Docker Compose (App + Banco com Persistência)
services:
  api:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      - SPRING_DATASOURCE_URL=jdbc:oracle:thin:@db:1521:XE
      - SPRING_DATASOURCE_USERNAME=system
      - SPRING_DATASOURCE_PASSWORD=senha_forte_aqui
      - SPRING_JPA_HIBERNATE_DDL_AUTO=update

  db:
    image: gvenzl/oracle-xe
    ports:
      - "1521:1521"
    environment:
      - ORACLE_PASSWORD=senha_forte_aqui
    volumes:
      - oracledata:/opt/oracle/oradata

volumes:
  oracledata:
