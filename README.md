# LugiaTracker

Sistema de gerenciamento de pátios, motos, gerentes, mecânicos e setores — desenvolvido como parte do desafio Java Advanced.

---

## 📌 Descrição

**LugiaTracker** é uma API RESTful construída com **Spring Boot** que permite o gerenciamento completo das operações em pátios veiculares. O sistema inclui o controle de motos, gerentes, setores e suas relações, utilizando boas práticas de desenvolvimento, cache, paginação, validação, documentação via Swagger e banco em memória (H2).

---

## ✅ Funcionalidades

- CRUD completo para as entidades:
  - `Gerente`
  - `Moto`
- Relacionamentos entre entidades (ex: Moto → Setor, Gerente → Pátio)
- Paginação e ordenação de resultados
- Busca por parâmetros
- DTOs para transferência segura de dados
- Validações com Bean Validation
- Cache de respostas com Spring Cache (`@Cacheable`)
- Tratamento global de exceções
- API documentada com Swagger/OpenAPI
- Banco H2 em memória com dados pré-carregados via SQL
- Uso de **Lombok** para reduzir a verbosidade do código

---

## 🧱 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Cache
- H2 Database
- Swagger (OpenAPI 3)
- **Lombok**
- Maven

---

## 🧰 Sobre o uso do Lombok

Este projeto utiliza a biblioteca [**Lombok**](https://projectlombok.org/) para reduzir a quantidade de código boilerplate nas classes, como:

- Getters e Setters automáticos (`@Getter`, `@Setter`)
- Construtores (`@NoArgsConstructor`, `@AllArgsConstructor`)
- Builder pattern (`@Builder`)
- `toString()`, `equals()` e `hashCode()` automáticos (`@ToString`, `@EqualsAndHashCode`)

> ⚠️ **Importante:** para que o Lombok funcione corretamente, é necessário instalar o plugin no seu IDE (ex: IntelliJ ou Eclipse) e habilitar o suporte a anotação.

---

## 🛠️ Como executar o projeto

### Pré-requisitos

- Java 17+
- Maven 3+
- IDE com suporte ao Lombok (IntelliJ, Eclipse etc.)

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/lugiatracker.git

# Acesse o diretório do projeto
cd lugiatracker

# Compile e execute o projeto
./mvnw spring-boot:run
