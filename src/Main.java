import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Mesa> BD_Mesa = new ArrayList<Mesa>();
        List<Garcom> BD_Garcom = new ArrayList<Garcom>();

        int opcao;


        do {
            // TODO: Deixar o menu bonitinho :)
            System.out.println(" 1. Cadastro de mesa\n 2. Remoção de mesa\n 3. Busca mesa pelo número\n 4. Busca mesa pela capacidade de clientes\n 5. relatório de mesas\n 0. sair\n");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o codigo da mesa");
                    int codigoMesa = sc.nextInt();
                    System.out.println("Numero da mesa");
                    int numeroMesa = sc.nextInt();
                    System.out.println("Situação da mesa");
                    String situacao = sc.next();
                    System.out.println("Capacidade de ocupação da mesa");
                    int capacidadeMaxima = sc.nextInt();
                    System.out.println("Cadastrado mesa");
                    Mesa mesa1 = new Mesa(codigoMesa,numeroMesa,situacao,capacidadeMaxima);
                    System.out.println(mesa1.getCodigoMesa());
                    break;
                case 2:
                    System.out.println("Mesa removida");
                    break;
                case 3:
                    System.out.println("Mesa encontrada");
                    break;
                case 4:
                    System.out.println("Mesa encontrada ");
                    break;
                case 5:
                    System.out.println("Relatorio das mesas");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }
}