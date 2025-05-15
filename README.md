# Sistema de Reclamações Pessoais

Este projeto é uma API REST desenvolvida em **Spring Boot** para o gerenciamento de reclamações pessoais. O sistema permite o registro, autenticação e envio de reclamações por usuários autenticados. A arquitetura segue o padrão **MVC** e utiliza boas práticas de segurança, validação e documentação.

---

## 💡 Objetivo do projeto

Criei este projeto com o intuito de explorar a integração do **MongoDB** com **Java**, a fim de entender as particularidades e o nível de dificuldade dessa implementação. Além disso, aproveitei para aprofundar meus conhecimentos em **Spring Security**, buscando aprimorar pontos que observei em exemplos anteriores — especialmente no que diz respeito à autenticação com JWT e ao controle de acesso às rotas da aplicação.

---

## 🧩 Diagrama de classes

``` mermaid
classDiagram
  direction LR
  class User {
    id: String
    firstName: String
    lastName: String
    email: String
    password: String
  }

  class Address {
    <<embedded>>
    street: String
    number: String
    neighborhood: String
    city: String
    state: String
    postalCode: String
    complement: String
  }
  
  class Complaint {
    id: String
    title: String
    description: String
    address: Address
    status: StatusEnum
    imageUrl: String
    createdDate: Instant
    updatedDate: Instant
    isAnonymous: boolean
  }

  class StatusEnum {
    PENDING
    UNDER_REVIEW
    IN_PROGRESS
    RESOLVED
    CLOSED
    REJECTED
  }

  class Tag {
    _id: String
    name: String
  }

  User "1"-- "*" Complaint
  Complaint "1"--"1" Address
  Complaint "*"-- "1" Tag

```

---

## 🛠 Tecnologias Utilizadas

- **Spring Boot**
- **MongoDB**
- **Spring Security**
- **JWT (Auth0)**
- **ModelMapper**
- **Swagger / OpenAPI**

---

## 📁 Estrutura do Projeto

### `config/`
Contém configurações gerais da aplicação. Atualmente, há o `ModelMapperConfig`, responsável pelo mapeamento entre entidades e DTOs.

### `model/`
Define as entidades utilizadas no banco de dados:
- `Complaint` (Reclamação), que possui o objeto aninhado `Address`
- `StatusEnum`, que define os possíveis status de uma reclamação

### `data/dto/`
Contém os **Data Transfer Objects (DTOs)** para comunicação entre cliente e servidor. Possui arquivos separados para:
- Criação (`Create`)
- Resposta (`Response`)
- Atualização (`Update`)
Além disso, implementa validações personalizadas nos campos.

### `repository/`
Interfaces para persistência dos dados, utilizando Spring Data com MongoDB.

### `service/`
Contém a lógica de negócio da aplicação. Destaque para:
- Registro e autenticação de usuários
- Manipulação das reclamações
- Subpasta `exceptions/` com exceções personalizadas

### `controller/`
Responsável pelo controle das rotas e endpoints da aplicação:
- Subpasta `docs/` com a configuração do Swagger/OpenAPI
- Subpasta `exceptions/` com tratamento global de erros via `ResourceExceptionHandler` e resposta padronizada (`StandardError`)

### `infra/security/`
Gerencia a segurança da aplicação:
- Geração e validação de tokens JWT
- Proteção de rotas com Spring Security

---
