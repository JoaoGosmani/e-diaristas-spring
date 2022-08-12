# Projeto E-Diaristas

Projeto desenvolvido durante a Imersão Multi Stack da [TreinaWeb](http://treinaweb.com.br/) utilizando Java e Spring Boot.

## Dependências do Projeto

- Spring Boot
- Spring Web MVC
- Thymeleaf
- Spring Data JPA
- Bean Validation

## Dependências de Desenvolvimento

- Spring Boot Devtools
- Lombok

## Requisitos

- Java 17
- Maven 3.8

## Como testar este projeto na minha máquina?

Clone este repositório e entre na pasta do projeto.

```sh
git clone https://github.com/JoaoGosmani/e-diaristas-spring.git
cd e-diaristas-spring
```

Atualize as configurações de acesso ao banco de dados no arquivo [application.properties](src/main/resources/application.properties).

```properties
spring.datasource.url=jdbc:mysql://host:porta/banco_de_dados?useTimeZone=true&serverTimeZone=America/Sao_Paulo
spring.datasource.username=usuario
spring.datasource.password=senha
```

Execute o projeto através do Maven.

```sh
mvn spring-boot:run
```

Acesse a aplicação em [http://localhost:8080/admin/servicos](http://localhost:8080/admin/servicos)