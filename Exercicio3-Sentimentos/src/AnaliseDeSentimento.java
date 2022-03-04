import java.util.Scanner;

public class AnaliseDeSentimento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String olho = ":";
        String nariz = "-";
        String bocaDivertido = ")";
        String bocaChateado = "(";

        int contadorRostoDivertido = 0;
        int contadorRostoChateado = 0;

        int teste = 0;


            System.out.println("\nInsira sua mensagem: ");
            String respostaDoUsuario[] = sc.nextLine().split("");
            int tamanhoDaFrase = respostaDoUsuario.length;


            for (int i = 0; i < tamanhoDaFrase; i++) {

                if (olho.equals(respostaDoUsuario[i])) {
                    if (nariz.equals(respostaDoUsuario[i+1])) {
                        if (bocaDivertido.equals(respostaDoUsuario[i+2])) {
                            contadorRostoDivertido++;
                        }else if (bocaChateado.equals(respostaDoUsuario[i+2])) {
                            contadorRostoChateado++;
                        }
                    }
                }

            }

        if(contadorRostoDivertido > contadorRostoChateado){
            System.out.println("Divertido");
        }
        else if(contadorRostoChateado > contadorRostoDivertido){
            System.out.println("Chateado");
        }
        else{
            System.out.println("Neutro");
        }


    }
}
