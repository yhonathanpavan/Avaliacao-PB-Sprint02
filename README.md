# :books: Avaliacao-PB-Sprint02 :computer:
#### Repositório referente aos exercícios avaliativos da sprint 02 - Programa de Bolsas - Compass.UOL

📝 Query usada no banco do exercício 01:
--------------------------------------------------
CREATE DATABASE db_produtos;

USE db_produtos;

CREATE TABLE PRODUTO (id INT AUTO_INCREMENT, nome VARCHAR(50) NOT NULL, descricao VARCHAR(255), quantidade INT, preco FLOAT, PRIMARY KEY (id)) Engine = InnoDB;

--------------------------------------------------


📝 Query usada no banco do exercício 02:
--------------------------------------------------
CREATE DATABASE db_filmes;

USE db_filmes;

CREATE TABLE filme (id INT AUTO_INCREMENT, nome VARCHAR(50) NOT NULL, descricao VARCHAR(255), ano YEAR, PRIMARY KEY (id)) Engine = InnoDB;

--------------------------------------------------

🔐 Login e senha utilizados para realizar conexão no JDBC: 
--------------------------------------------------

- Login: root
- Senha: toor123

🗃 Dependências:
--------------------------------------------------
- Os exercícios 1 e 2 precisam de um driver / biblioteca (JAR) para acessar o banco de dados.
- O driver usado para fazer a conexão com o banco foi o MySQl-Connector Versão 8.0.17
- Esta biblioteca está disponível para download na pasta "MySQL Connector - JAR Download"
--------------------------------------------------
