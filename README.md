# LocBem API

Trata-se  de uma API voltada para  a capacidade de servir e possibilitar o desenvolvimento de um sistema multilateral de seleção de ofertas para locação de veículos utilizando chatbot e linguagens web com estratégicas matemáticas de multicritérios.

### **📋 Pré-requisitos**

Para executar o projeto, será necessário instalar os seguintes programas:

<aside>
💡 - JDK 11                                                                                                                                                            -Maven 3.8.1                                                                                                                                      -Docker Desktop

</aside>

****🔧 Construção****

Para construir o projeto com o Maven, executar os comando abaixo:

```java

mvn clean install
```

O comando irá baixar todas as dependências do projeto e criar um diretório *target*
 com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

## Banco de Dados

Para criar um banco de dados para o sistema basta executar o comando a abaixo:

```java
docker-compose up
```

## Testes

Para rodar os testes, utilize o comando abaixo:

```java
mvn test
```

## Documentação

Para ter acesso a documentação  acesse os seguintes arquivos:

