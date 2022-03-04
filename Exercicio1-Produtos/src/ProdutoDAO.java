import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection){
        this.connection = connection;
    }

    public void listar() throws SQLException {

        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM produto")){
            ps.execute();

            try(ResultSet rs = ps.getResultSet()){

                while(rs.next()){
                    Integer id = rs.getInt(1);
                    String nome = rs.getString(2);
                    String desc = rs.getString(3);
                    Integer quantidade = rs.getInt(4);
                    Double preco = rs.getDouble(5);

                    System.out.println("************************************");
                    System.out.println(" Id: " + id);
                    System.out.println(" Nome: " + nome);
                    System.out.println(" Descricao: " + desc);
                    System.out.println(" Quantidade: " + quantidade);
                    System.out.println(" Preco: R$" + preco);
                    System.out.println("************************************\n");

                }
            }
        }
    }

    public void inserir(Produto produto) throws SQLException {

        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, QUANTIDADE, PRECO) VALUES (?,?,?,?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setInt(3, produto.getQuantidade());
            pstm.setDouble(4, produto.getPreco());

            pstm.execute();

            try (ResultSet rs = pstm.getGeneratedKeys()) {

                while (rs.next()) {
                    produto.setId(rs.getInt(1));
                    System.out.println("Foi adicionado o item de código: " + produto.getId());
                }
            }
        }
    }

    public void remover(Integer id) throws SQLException {

        String sql = "DELETE FROM PRODUTO WHERE ID = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setInt(1, id);
            pstm.execute();

            Integer linhasModificadas = pstm.getUpdateCount();

            System.out.println("O item com id: " + id + " foi excluído!");
            System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);

        }
    }

    public void atualizar(Produto produto, int id) throws SQLException{

        Scanner sc = new Scanner(System.in);

        System.out.println("Você escolheu alterar o produto de Id = " + id);
        System.out.println("Insira o novo nome do produto: ");
        produto.setNome(sc.nextLine());
        System.out.println("Insira a nova descricao do produto: ");
        produto.setDescricao(sc.nextLine());
        System.out.println("Insira sua quantidade: ");
        produto.setQuantidade(sc.nextInt());
        System.out.println("Insira seu preco: ");
        produto.setPreco(sc.nextDouble());

        try(PreparedStatement pstm = connection.prepareStatement("UPDATE PRODUTO SET NOME = ?, DESCRICAO = ?, QUANTIDADE = ?, PRECO = ? WHERE ID = ?")){
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setInt(3, produto.getQuantidade());
            pstm.setDouble(4, produto.getPreco());
            pstm.setInt(5, produto.getId());

            pstm.execute();
        }
    }

    //Armazena os produtos do meu banco em uma lista
    public  List<Produto> ArmazenarLista() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM PRODUTO";

        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.execute();

            try(ResultSet rs = pstm.getResultSet()){

                while(rs.next()){
                    Produto produto = new Produto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }

}
