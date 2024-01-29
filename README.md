## Api de Cadastro de Beneficiarios

---

## ⚙️ Funcionalidades

- Cadastrar um beneficiario junto com seus documentos.
- Listar todos os beneficiarios cadastrados.
- Listar todos os documentos de um beneficiário a partir de seu id.
- Atualizar os dados cadastrais de um beneficiário
- Remover um beneficiário

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[Lombok](https://projectlombok.org)**

---

## 📄 Documentação

Caso a aplicação esteja no ar, a documentação e as requisições das funcionalidades da aplicação pode ser acessada via Swagger no link: <a href="http://localhost:8080/swagger-ui/index.html#">Swagger</a>

O Swagger está disponível no projeto no folder swagger, arquivo: swagger-api.json.

O banco de dados usado no projeto foi o MySQL 8.0, sendo necessário criar manualmente os bancos:

- api_beneficiarios_saude
- api_beneficiarios_saude_test

Para requisição de token via API, é necessário enviar no payload o usuario cadastrado "admim" e a senha "123456" no endpoint de login:

{
  "login": "admin",
  "senha": "123456"
}

O projeto foi criado com as tecnologias citadas acima, tendo dois dominios: beneficiario e documento. 
- As camadas usadas foram Controller, Service e Repository e Records para transitar os payloads de requisições e respostas.

- Para tratamento de exceções foi usado a anotação @RestControllerAdvice, visando centralizar em um ponto especifico o tratamento das principais exceções. 

- Para a autenticação e autorização foi utilizado as funcionalidades do Spring Security, com token no formato JWT, usando o algoritmo BCrypt,
e para validação dos tokens recebidas nas requisições foi usada a biblioteca do Oauth0.

- O Flyway foi usado para os incrementos das queries para criação de tabelas e criação de usuario.

- Testes foram usados o Mockito com Junit.


