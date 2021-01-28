# [CAD Funcionários](https://github.com/georgypassos/cadfuncionarios)

Este é um microserviço da API responsável pelo Gerenciamento do Cadastro de Funcionários da empresa Fictícia Ltda.

## Construção da aplicação

A aplicação foi construída com:

  - Java 8;
  - Spring Boot 2.4.2;
  - [Swagger](https://swagger.io/) para a documentação das APIs;
  - [H2](https://github.com/h2database/h2database) como base de dados (in memory).

Tests:

  - [JUnit](https://github.com/junit-team/junit5) framework;
  - MockMvc para chamada dos endpoints REST.
  
### Estrutura do projeto
      + config/
          Configuração da aplicação: Swagger; Segurança (JWT); etc.
      + exception/
          Definição das classes para controle de exceções; 
      + persistence/*
          Camada de persistência de dados e definição de tabelas;
      + service/
          Camada responsável pela lógica de negócios; 
      + web/*
          Classes e métodos para controle de ações da API;
      - CadfuncionariosApplication.java <- The main class

### Base de Dados

Usa base de dados H2 em memória (no modo embbeded), veja `application.properties` para mais detalhes.

## Iniciando a aplicação

É necessário ter a JVM instalada;

O servidor é configurado para iniciar na porta **8100**, mas isso pode ser alterado em `application.properties`.

### 1) Executar testes e construir:

> mvn clean verify;

### 2) Iniciar o serviço:

> mvn exec:java;

### 3) Acessar o console da base de dados H2:

> [http://localhost:8100/h2-console](http://localhost:8100/h2-console);

> URL do JDBC: "jdbc:h2:mem:cadfuncionariosdb" (definido em `application.properties`)

### 4) Então, você já pode utilizar a aplicação:

> Endpoint disponível de acesso público:

    - GET -> /api/public/funcionarios/ : retorna todos os funcionários ativos da base
    
> Endpoints disponíveis que exigem autenticação:    
    
1) primeiro deve ser feita a autenticação, através de uma API, e receber um token:

*obs: Ainda não há base de dados para realizar a autenticação de usuários, no entanto ela é realizada de forma estática para fins de teste; com isso, use o seguinte usuário e senha, respectivamente:*
**usuario01 / password**
  
```shell
	> curl -H "Content-Type: application/json" \
	  --request POST \
	  --data '{"username":"usuario01","password":"password"}' \
	  http://localhost:8100/api/authenticate
```
	
	ex. JSON de retorno: 
	
	{"token":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c3VhcmlvMDEiLCJleHAiOjE2MTE4ODQ0MjAsImlhdCI6MTYxMTc5ODAyMH0.ajQr0nPvtKinzP4dBXg20SY5HAuI5T5EUs2TYD74zjnLW2ZIZ1AjHh6vrNxyJp12Zg5FJH5yGirxvrabbZ2bJg"}

2) após recuperação do token, ele deve ser passado no header no atributo **Authorization**, por exemplo:

**API para obter um funcionário pelo ID:**

```shell
	> curl -H "Content-Type: application/json" \
			-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c3VhcmlvMDEiLCJleHAiOjE2MTE4ODQ0MjAsImlhdCI6MTYxMTc5ODAyMH0.ajQr0nPvtKinzP4dBXg20SY5HAuI5T5EUs2TYD74zjnLW2ZIZ1AjHh6vrNxyJp12Zg5FJH5yGirxvrabbZ2bJg" \
	  -X GET \
	  http://localhost:8100/api/funcionarios/101
```

### 5) Para visualizar a documentação completa, explicando como os clientes devem consumir as APIs, dê uma olhada em:

> [http://localhost:8100/swagger-ui/index.html](http://localhost:8100/swagger-ui/index.html)