import java.sql.*;
import java.util.Scanner;

public class ExecutaPrograma {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().getConnection()){

            FilmeDAO filmeDAO = new FilmeDAO(connection);

            int opcaoMenu = 0;
            Integer qtdFilmes = null;
            Integer pagina = null;

            while(opcaoMenu != 4){

                System.out.println("----------- Menu -----------");
                System.out.println("1. Insira a quantidade de filmes por página");
                System.out.println("2. Selecione a página que deseja visualizar");
                System.out.println("3. Ver catálogo");
                System.out.println("4. Sair");
                System.out.printf("Opção: ");
                opcaoMenu = sc.nextInt();

                switch (opcaoMenu) {

                    case 1:
                        System.out.println("\nQuantos filmes você deseja ver?");
                        qtdFilmes  = sc.nextInt();
                        break;

                        case 2:
                            System.out.println("\nQual página você você deseja acessar?");
                            pagina = sc.nextInt();
                            break;

                        case 3:
                            //Try para impedir que o usuário tente ver o catálogo sem informar a quantidade de filmes ou qual página quer acessar.
                            try {
                                filmeDAO.construirPaginacao(qtdFilmes, pagina);
                            }catch(NullPointerException ex){
                                System.out.println("\nPor favor, verifique se os dados da opção 1 e 2 foram preenchidos\n");
                            }
                            break;

                        case 4:
                            System.out.println("\nSaindo... Aguarde...");
                        break;

                        default:
                             System.out.println("\nOpção inválida!\n");
                        break;
                }
            }
            System.exit(1);
        }

    }

}

