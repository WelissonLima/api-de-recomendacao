![Captura de Tela 2021-04-16 às 13 27 04](https://user-images.githubusercontent.com/62679423/115055736-3ed13080-9eb8-11eb-8b94-8282df27b68c.png)

<h1><b><i>
Recomendação de Filmes (API)
</h1></i></b>

<h2>
Baseando-se nos filmes que você viu e nas notas que deu para cada um deles, iremos te indicar filmes baseados no seu gosto.
</h2>

<h3>
Tecnologias utilizadas no projeto:
</h3>
<p>
- Java 11 <br>
- H2 Database<br>
- Spring boot 2.4.4
</p>

<h3>
Como rodar o projeto ?
</h3>
<p>
Linux(Terminal):<br>
<b>java -jar projeto.jar</b><br><br>

MacOS(Terminal):<br>
<b>java -jar projeto.jar</b><br><br>

Windows(CMD):<br>
<b>java -jar projeto.jar</b><br><br>
</p>

<b><i>
Obs.: O projeto irá rodar na porta 8080.
</i></b>

<h2><b>
Endpoints
</b></h2>

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
