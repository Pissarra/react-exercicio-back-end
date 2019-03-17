### Pré-requisitos

#### Criar banco de dados e configurar acesso no arquivo application.properties

```
spring.datasource.url=jdbc:postgresql://localhost:5432/exercicio
spring.datasource.username=postgres
spring.datasource.password=postgres
```

#### Inserir no banco de dados

```
insert into user_app values(1, 'ADMIN','admin', '123456');
insert into user_app values(2, 'COMUM','comum', '123456');
```

#### Rodar o projeto

```
gradlew build
java -jar build/libs/react-0.0.1-SNAPSHOT.jar
```
