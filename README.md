![Captura de Tela 2021-04-16 às 13 27 04](https://user-images.githubusercontent.com/62679423/115055736-3ed13080-9eb8-11eb-8b94-8282df27b68c.png)

# Recomendação de Filmes (API)
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/WelissonLima/api-de-recomendacao/blob/master/LICENSE)


# Sobre o projeto

(colocar link do projeto no Heroku - quando estiver lá)

Recomendação de Filmes é uma aplicação backEnd construída durante o 4° semestre da Faculdade, na Disciplina de Programação Orientada a Objetos.

A aplicação consiste em fazer uma recomendação de filmes, assistidos por um determinado usuário, baseando-se na similaridade que houver entre o que recomenda, e o que vai recebeer a recomendação. A similaridade entre os usuários será feita com base nas semelhanças entre as notas que os usuários darão para os Filmes assistidos. Dessa forma a API irá recomendar um filme fundamentado no seu gosto.


# Tecnologias utilizadas 

## BackEnd
- Java (versão 11)
-  Spring Boot (versão 2.4.4)
-  JPA/ Hibernate
-  Maven
-  H2 Database

## Implantação em Produção
- BackEnd: Heroku (Ao finalizar o projeto)
- Banco de dados: MySQL

# Como executar o projeto

## BackEnd
Pré-requisitos: Java 11

### Rodar direto pelo cmd

```bash
# clonar o repositório do projeto
git clone https://github.com/WelissonLima/api-de-recomendacao.git

# entrar na pasta onde foi feita a clonagem, e abrir um promt local dentro do projeto (git bash - pra quem tem)
cd nome-projeto

#executar o projeto com o seguinte código:

# Windows
java -jar projeto.jar

# Linux
java -jar projeto.jar

# MacOS
java -jar projeto.jar

```
### <i>ATENÇÃO: Por Padrão, o projeto irá rodar na porta 8080. (localhost:8080)</i>




# <b>Endpoints</b>

| Método  |  URL  | Descrição  |
| ------------------- | ------------------- | -------------------- |
| GET  | http://localhost:8080/usuarios | Listar os usuários. |
| POST | http://localhost:8080/usuarioso | Adicionar um novo Usuario |
| PUT | http://localhost:8080/usuarios | Atualizar os dados de um  usuário. |
| DELETE | http://localhost:8080/usuarios | Deletar um usuário. |
| GET |  http://localhost:8080/usuarios/{usuarioID} | Listar os filmes de um determinado usuário. |
| GET |  http://localhost:8080/filmes | Listar Todos os filmes|
| POST |  http://localhost:8080/filmes | Adicionar um novo filme |
| PUT |  http://localhost:8080/filmes | Atualizar um filme |
| DELETE |  http://localhost:8080/filmes | Deletar um filme |
| GET |  http://localhost:8080/similaridade/{usuarioID} | Mostra a similaridade de um usuário com os outros |
| GET  |  http://localhost:8080/recomendacao/{usuarioID} |  Recomenda alguns filmes para um determinado usuário. |
