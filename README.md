# 🩺 Voll.med API – Projeto com Spring Boot

Este repositório contém o desenvolvimento de uma API REST para a clínica fictícia **Voll.med**, construída como parte do curso da [Alura](https://www.alura.com.br) ministrado por [Rodrigo Ferreira](https://cursos.alura.com.br/user/rodrigo-ferreira). O projeto foi utilizado como base para a aplicação de boas práticas em arquitetura de software, segurança e desenvolvimento de APIs com **Spring Boot**.

---

## 💡 Sobre o Projeto

A **Voll.med** é uma clínica médica fictícia que precisa de um sistema para gerenciar seus atendimentos. A proposta do projeto é desenvolver uma API REST robusta que permita:

* Cadastro de médicos e pacientes;
* Agendamento de consultas;
* Login com autenticação via JWT para acesso seguro às rotas protegidas.

Enquanto o aplicativo mobile será desenvolvido por outro time, este repositório concentra a implementação do **backend da aplicação**.

---

## 🚀 Funcionalidades

* [x] CRUD de médicos
* [x] CRUD de pacientes
* [x] Agendamento de consultas
* [x] Autenticação com login via JWT
* [x] Paginação e ordenação em listagens
* [x] Testes automatizados (unitários)

---

## 🔮 Funcionalidades Futuras

Funcionalidades que pretendo adicionar ou aprimorar no projeto:

* [ ] Cancelamento de consultas
* [ ] Testes automatizados de integração
* [ ] Controle de acesso mais granular com diferentes níveis de permissão
* [ ] Integração com serviços externos (ex: envio de e-mail ou notificações)
* [ ] Melhorias na cobertura de segurança

---

## 🧱 Estrutura e Boas Práticas

Durante o desenvolvimento, foram adotadas práticas modernas para garantir um código limpo, organizado e seguro:

* **Arquitetura em camadas**: separação clara entre `Controllers`, `Services`, `Repositories` e `DTOs`
* **Validações** com Annotations do Bean Validation e tratamentos centralizados de exceções
* **Autenticação e segurança** com JWT e Spring Security
* **Migrações de banco de dados** automatizadas com Flyway
* **Padrões RESTful** com endpoints bem definidos
* **Documentação automática com SpringDoc/Swagger**, facilitando testes e entendimento dos endpoints

---

## 🔐 Segurança

A API protege as rotas sensíveis utilizando:

* Autenticação via **JWT (JSON Web Token)**
* Filtros personalizados de segurança

---

## 🧪 Testes (em construção)

A aplicação está preparada para expansão com testes automatizados de unidade e integração utilizando JUnit e Spring Test.

---

## 🎨 Layout

O layout do aplicativo mobile está disponível no Figma:
🔗 [Clique para acessar o Figma](https://www.figma.com/file/N4CgpJqsg7gjbKuDmra3EV/Voll.med)

---

## 📄 Documentação de Funcionalidades

As funcionalidades foram organizadas e documentadas na seguinte board do Trello:
🔗 [Clique para acessar o Trello](https://trello.com/b/O0lGCsKb/api-voll-med)

---

## ⚙️ Tecnologias Utilizadas

* [Java 21](https://www.oracle.com/java)
* [Spring Boot 3](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org)
* [MySQL](https://www.mysql.com)
* [Hibernate](https://hibernate.org)
* [Flyway](https://flywaydb.org)
* [Lombok](https://projectlombok.org)
* [Spring Security](https://spring.io/projects/spring-security)
* [JWT](https://jwt.io)

---

## 📘 Licença e Créditos

Este projeto foi desenvolvido com base no curso da [Alura](https://www.alura.com.br) com o instrutor [Rodrigo Ferreira](https://cursos.alura.com.br/user/rodrigo-ferreira).
O código foi adaptado, expandido e aprimorado como parte do meu aprendizado em desenvolvimento backend com Java e Spring Boot.

---

## 📫 Contato

Caso queira acompanhar mais projetos ou entrar em contato:

[![GitHub](https://img.shields.io/badge/GitHub-000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/lukaskardeck)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/lukaskardeck)