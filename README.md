# Universidade FIAP - Projeto Tracking Code

## Descrição

Aplicação Spring Boot para gerenciamento de usuários, funções, setores e endereços.
Banco de dados: **PostgreSQL**
Migrations: **Flyway**

## Pré-requisitos

* Java 17+
* Maven 3.8+
* PostgreSQL 12+ (ou superior)

## Configuração do Banco de Dados

1. Crie o banco `trackingdb`:

```sql
CREATE DATABASE trackingdb;
```

2. Configure usuário e senha em `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/trackingdb
spring.datasource.username=postgres
spring.datasource.password=123456
spring.datasource.driver-class-name=org.postgresql.Driver
```

3. Flyway configurado para aplicar migrations automaticamente:

```properties
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.locations=classpath:db/migration
```

## Rodando o Projeto

1. Compile e instale o projeto:

```bash
mvn clean install -DskipTests
```

2. Execute a aplicação:

```bash
mvn spring-boot:run
```

3. Acesse:

```
http://localhost:8080
```

## Migrations Aplicadas

* `v1__drop.sql` → Remove tabelas antigas
* `v2__create.sql` → Cria tabelas e tipos ENUM
* `v3__constraints.sql` → Adiciona foreign keys
* `v4__inserts.sql` → Popula banco com dados iniciais (usuário admin, funções, setores, endereço)

## Usuário Admin

* **Login:** `admin`
* **Senha:** `admin` (hash BCrypt já inserido via migration)

## Testando o Banco

```sql
SELECT * FROM usuario;
SELECT * FROM funcao;
SELECT * FROM setor;
SELECT * FROM endereco;
```

## Observações

* Spring Security, JPA/Hibernate e Thymeleaf estão configurados.
* Avisos sobre `frameOptions()` deprecated e `spring.jpa.open-in-view` podem ser ignorados.
* Flyway alerta que PostgreSQL 18 não é oficialmente suportado, mas funciona em ambiente local.
