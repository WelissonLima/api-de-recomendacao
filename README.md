![Captura de Tela 2021-04-16 às 13 27 04](https://user-images.githubusercontent.com/62679423/115055736-3ed13080-9eb8-11eb-8b94-8282df27b68c.png)

<h1><b><i>
API de Recomendação de Filmes
</h1></i></b>

<h2>
Baseando-se nos filmes que você viu e nas notas que deu para eles, iremos indica-lo filmes que você possa gostar.
</h2>

<h3>
Técnologias utilizadas:
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
| GET  | http://localhost:8080/usuarios | Listar os usuário. |
| POST | http://localhost:8080/usuarioso | Adicionar Usuario |
| PUT | http://localhost:8080/usuarios | Atualizar um usuário. |
| DELETE | http://localhost:8080/usuarios | Deletar um usuário. |
| GET |  http://localhost:8080/usuarios/{usuarioID} | Lista os filmes de um usuário. |
| GET |  http://localhost:8080/filmes | Listar os filme |
| POST |  http://localhost:8080/filmes | Adicionar um filme |
| PUT |  http://localhost:8080/filmes | Atualizar um filme |
| DELETE |  http://localhost:8080/filmes | Deletar um filme |
| GET |  http://localhost:8080/similaridade/{usuarioID} | Mostra a similaridade de um usuário com os outros |
| GET  |  http://localhost:8080/recomendacao/{usuarioID} |  Recomenda alguns filmes para um determinado usuário. |
