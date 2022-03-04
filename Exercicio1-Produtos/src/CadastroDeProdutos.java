import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroDeProdutos {

    static Scanner sc = new Scanner(System.in);
    static List<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) throws SQLException {

        int opcaoMenu = 0;

        try(Connection connection = new ConnectionFactory().getConnection()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);


            while (opcaoMenu != 5) {

                 produtos = produtoDAO.ArmazenarLista(); //Atribuo os produtos do banco de dados para minha lista

                opcaoMenu = menu(); //Invoco o menu e recebo a opção que o usuário escolheu

                switch (opcaoMenu) {

                    case 1:
                        produtoDAO.inserir(setValorProduto());
                        break;

                    case 2:
                        System.out.println("\nAtualizar");
                        atualizaProduto(produtos, produtoDAO);
                        break;

                    case 3:
                        System.out.println("\nExcluir");
                        System.out.println("Insira o id do produto que deseja excluir: ");
                        int idAExluir = sc.nextInt();
                        produtoDAO.remover(idAExluir);
                        break;

                    case 4:
                        System.out.println("\nLista de produtos cadastrados: ");
                        produtoDAO.listar();
                        break;

                    case 5:
                        System.out.println("\nSaindo...");
                        break;

                    default:
                        System.out.println("Opção inválida!!");
                        break;
                }
            }
            System.exit(1);
        }

    }

    private static void atualizaProduto(List<Produto> produtos, ProdutoDAO produtoDAO) throws SQLException {

        Produto produtoAAtualizar = null;
        Integer idAAtualizar = null;

        System.out.println("Insira o id do produto que deseja atualizar: ");
        int id = sc.nextInt();

        //Verifico se o id do produto que pretendo atualizar existe
        for (int i = 0; i < produtos.size(); i++) {
            if(id == produtos.get(i).getId()){
                produtoAAtualizar = produtos.get(i);
                idAAtualizar = produtos.get(i).getId();
                break;
            }
        }

        //Validação caso o produto não exista na minha lista
        if(idAAtualizar != null){
            produtoDAO.atualizar(produtoAAtualizar, idAAtualizar);
        }else{
            System.out.println("Produto não encontrado! ");
        }
    }

    private static Produto setValorProduto() {
        sc.nextLine(); //apenas para remover o "enter" da entrada anterior.
        System.out.println("\nInserir");

        Produto produto = new Produto();

        System.out.println("Insira o nome do produto: ");
        produto.setNome(sc.nextLine());
        System.out.println("Insira o descricao do produto: ");
        produto.setDescricao(sc.nextLine());
        System.out.println("Insira a quantidade: ");
        produto.setQuantidade(sc.nextInt());
        System.out.println("Insira o preco: ");
        produto.setPreco(sc.nextDouble());

        return produto;
    }

    private static int menu() {

        System.out.println("\n------ Menu Opções --------");
        System.out.println("1. Cadastrar produtos");
        System.out.println("2. Atualizar produto");
        System.out.println("3. Excluir produto");
        System.out.println("4. Listar produtos");
        System.out.println("5. Sair");
        return sc.nextInt();
    }
}
