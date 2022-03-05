import java.util.Scanner;

public class AnaliseDeSentimento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String rostoFeliz = ":-)";
        String rostoChateado = ":-(";
        String msg = "";

        while(!msg.equals("0")) {

            int contadorRostoFeliz = 0;
            int contadorRostoChateado = 0;

            System.out.println("\nPara sair do programa, basta digitar \"0\"");
            System.out.println("Insira sua mensagem: ");
            msg = sc.nextLine();

            for (int i = 0; i < msg.length(); i++) {
                if (msg.substring(i).startsWith(rostoFeliz)){
                    contadorRostoFeliz++;
                }
                if (msg.substring(i).startsWith(rostoChateado)){
                    contadorRostoChateado++;
                }
            }

            if(contadorRostoFeliz > contadorRostoChateado){
                System.out.println("Divertido");
            }else if(contadorRostoChateado > contadorRostoFeliz){
                System.out.println("Chateado");
            }else if(msg.equals("0")) {
                System.out.println("Saindo....");
            }else{
                System.out.println("Neutro");
            }
        }
        System.exit(1);

    }
}
