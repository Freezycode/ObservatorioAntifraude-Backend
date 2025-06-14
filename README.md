Definição do projeto:

Observador Antifraude - Cadastro de relatos sobre fraudes cibernéticas

Problema:

Fraudes têm ocorrido por falsificação das interfaces e comunicações que imitam os sistemas do SERPRO, levando ao engano de muitos usuários e afetando a credibilidade e confiança nos serviços da instituição.

Solução:

Uma solução viável seria um sistema que permita que qualquer pessoa física (seja vítima ou não) consiga reportar seu relato de maneira eficaz e segura, os quais serão feitos via formulário contendo funcionalidade devolutiva ao usuário.

Como executar o projeto:
Clone o repositório com o comando:
git clone https://github.com/Freezycode/ObservatorioAntifraude-Backend

Abra a pasta do projeto no Visual Studio Code, IntelliJ, Spring Tools Suite ou outra IDE de sua preferência.

A IDE fará o download automático das dependências via Maven.

Caso isso não ocorra, execute no terminal do projeto o comando:
./mvnw clean install

Localize o arquivo application.properties para configurar a conexão com o banco de dados (URL, usuário, senha e nome do banco).

Execute o script de criação do banco de dados que está no caminho:
src/main/resources/forum_relatos.sql

Execute esse script no PostgreSQL para criar as tabelas necessárias.

Com o banco de dados criado e configurado, execute a aplicação pelo terminal com o comando:
./mvnw spring-boot:run

Ou pelo botão Run da sua IDE.

A aplicação estará disponível no endereço:
http://localhost:8080/

Para testar os endpoints, utilize o Postman:

Abra o Postman.

Clique em Importar.

Navegue até a pasta:
src/postman/Observatorio-antifraudes.postman_collection.json

Importe o arquivo de coleção.

A coleção contém os seguintes endpoints organizados para testes:

Usuário: (GET, POST, PUT, DELETE)

Relatos: (GET, POST, PUT, DELETE)

Status do Relato: (GET, POST)

Código da Consulta: (GET)
