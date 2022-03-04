import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmeDAO {

    Connection connection;
    Scanner sc = new Scanner(System.in);

    FilmeDAO(Connection con){
        this.connection = con;
    }

    public void construirPaginacao(int qtdFilmesPorPagina, int paginaAcessada) throws SQLException {

        InserirFilmes(connection);

        int quantidadeDeFilmes = ArmazenarLista().size();

        double qtdDePaginasDisponiveis = Math.ceil(quantidadeDeFilmes / (float)qtdFilmesPorPagina);

        listar(qtdFilmesPorPagina, paginaAcessada, qtdDePaginasDisponiveis);
    }

    public void listar(int qtdFilmesPorPagina, int paginaAcessada, double qtdDePaginasDisponiveis) throws SQLException{

        int contadorDePaginas = 1;
        int limiteInicial = 0;
        int limiteFinal = 0;

        //Validação de acesso a páginas inexistentes
        if(paginaAcessada < 1 || paginaAcessada > qtdDePaginasDisponiveis){
            System.out.println("\nPágina não disponível! ");
            System.out.println("Quantidade de páginas possíveis para acessar: " + (int)qtdDePaginasDisponiveis + ".  | Pág inserida: "+ paginaAcessada + ".");
            System.out.println("Por favor, altere para  uma página disponível! \n");
        }else{
            System.out.println("\n----------------------------------");
            System.out.println("Você está na página: " + paginaAcessada + "/" + (int)qtdDePaginasDisponiveis);
            System.out.println("----------------------------------\n");
            while(contadorDePaginas <= qtdDePaginasDisponiveis) {

                if(paginaAcessada == contadorDePaginas){

                    limiteInicial = ((qtdFilmesPorPagina*contadorDePaginas) - qtdFilmesPorPagina);
                    limiteFinal = qtdFilmesPorPagina;
                    break;
                }
                contadorDePaginas++;
            }


        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM FILME LIMIT ?, ?")){
            ps.setInt(1, limiteInicial);
            ps.setInt(2, limiteFinal);
            ps.execute();


            try(ResultSet rst = ps.getResultSet()){

                while(rst.next()){
                    Integer id = rst.getInt("ID");
                    String nome = rst.getString("NOME");
                    String desc = rst.getString("DESCRICAO");
                    int ano = rst.getInt("ANO");

                    System.out.println("************************************");
                    System.out.println(" Id: " + id);
                    System.out.println(" Nome: " + nome);
                    System.out.println(" Descricao: " + desc);
                    System.out.println(" Ano: " + ano);
                    System.out.println("************************************\n");
                }

            }
          }
        }
    }

    //Armazena os filmes da minha tabela em um arraylist
    public List<Filme> ArmazenarLista() throws SQLException {
        List<Filme> filmes = new ArrayList<>();

        String sql = "SELECT * FROM FILME";

        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.execute();

            try(ResultSet rs = pstm.getResultSet()){

                while(rs.next()){
                    Filme filme = new Filme(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    filmes.add(filme);
                }

            }

        }
        return filmes;
    }


    private void InserirFilmes(Connection con) throws SQLException {
        try(Statement stm = con.createStatement()){

            //"Drop" da tabela para cada vez que o programa iniciar, os ids e a quantidade de filmes permanecerem os mesmos.
            stm.execute("DROP TABLE IF EXISTS FILME");
            stm.execute("CREATE TABLE FILME (id INT AUTO_INCREMENT, nome VARCHAR(50) NOT NULL, descricao VARCHAR(255), ano YEAR, PRIMARY KEY (id)) Engine = InnoDB;");

            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('O que ficou pra trás', 'Direção de Remi Weekes, Reino Unido/EUA.', 2020)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Os Lobos', 'Direção de Samuel Kishi, México', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Rede de Ódio', 'Direção de Jan Komasa, Polônia', 2020)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Os Perseguidos', 'Direção de Melina Matsoukas, EUA', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('A Primeira Vaca', 'Direção de Kelly Reichardt, EUA', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Lindinhas', 'Direção de Maïmouna Doucouré, França', 2016)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Nunca Raramente Às Vezes Sempre', 'Direção de Eliza Hittman, EUA/Reino Unido', 2020)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Druk', 'Direção de Thomas Vinterberg, Holanda', 2020)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('O Poço', 'Direção de Galder Gaztelu-Urrutia, Espanha', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('E Então Nós Dançamos', 'Direção de Levan Akin, Geórgia/Suíça', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Devorar', 'Direção de Carlo Mirabella-Davis, EUA', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Viveiro', 'Direção de Lorcan Finnegan, Irlanda', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Nós Duas', 'Direção de Filippo Meneghetti, França', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('White Lie', 'Direção de Yonah Lewis & Calvin Thomas, Canadá', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Joias Brutas', 'Direção de Josh Safdie & Benny Safdie, EUA', 2020)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Os Miseráveis', 'Direção de Ladj Ly, França', 2020)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('04. 911 (idem)', 'Direção de Tarsem Singh, EUA', 2020)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('03. 1917 (idem)', 'Direção de Sam Mendes, Reino Unido', 2020)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('O Chalé', 'Direção de Veronika Franz & Severin Fiala, Reino Unido/EUA', 2019)");
            stm.execute("INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES ('Nova Ordem', 'Direção de Michel Franco, México', 2020)");
        }
    }

}


