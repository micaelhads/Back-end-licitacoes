# Back-end-licitacoes



> Projeoto feito com intuito de buscar licitações a partir de um conteudo html e manipula-las

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Java 17
* maven 3.2.5
* Spring boot 3.1.4

## 🚀 Instalando

Para instalar o <nome_do_projeto>, siga estas etapas:

Windows:

Basta apenas rodar o comando para baixar as dependências
```
mvn clean install
```

## ☕ Inciando o projeto

Para usar o proejeto, siga estas etapas:


Utilize o comando em um terminal
```
mvn spring-boot:run
```
Ou inicie o projeto a partir de uma ide de sua preferência 

## ☕ Endpoints

Capturar e gravar licitações na base de dados:
* /licitacoes/capturarSalvarLicitacoes

Deveolver todas as licitações da base de dados
* /licitacoes/getlicitacoes

Método utilizado para gravar ou atualizar uma licitação
* /licitacoes/gravar
