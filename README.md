# Definição do Projeto

**Observador Antifraude** – Cadastro de relatos sobre fraudes cibernéticas.

---

# Problema

Fraudes têm ocorrido por falsificação das interfaces e comunicações que imitam os sistemas do SERPRO, levando ao engano de muitos usuários e afetando a credibilidade e confiança nos serviços da instituição.

---

# Solução

Uma solução viável seria um sistema que permita que qualquer pessoa física (seja vítima ou não) consiga reportar seu relato de maneira eficaz e segura, via formulário contendo funcionalidade devolutiva ao usuário.

---

# Como Executar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/Freezycode/ObservatorioAntifraude-Backend
```

2. Abra a pasta do projeto na sua IDE preferida (Visual Studio Code, IntelliJ, Spring Tools Suite, etc).

3. A IDE fará o download automático das dependências via Maven.

   * Caso não ocorra automaticamente, execute no terminal do projeto:

   ```bash
   ./mvnw clean install
   ```

4. Configure a conexão com o banco de dados PostgreSQL:

   * Abra o arquivo `application.properties`.
   * Configure URL, usuário, senha e nome do banco.

5. Execute o script SQL para criação das tabelas:

   * Localize o arquivo:
     `src/main/resources/forum_relatos.sql`
   * Execute esse script no PostgreSQL para criar as tabelas necessárias.

6. Execute a aplicação:

   * Pelo terminal:

   ```bash
   ./mvnw spring-boot:run
   ```

   * Ou pelo botão **Run** da sua IDE.

7. A aplicação estará disponível em:
   `http://localhost:8080/`

# Endpoints Disponíveis na Coleção

* **Usuário:**

  * POST

* **Cadastro do Relato:**

  * POST

* **Status do Relato:**

  * GET

* **Código da Consulta:**

  * GET

