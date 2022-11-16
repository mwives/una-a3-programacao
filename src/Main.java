import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Mesa> BD_Mesa = new ArrayList<Mesa>();
    List<Garcom> BD_Garcom = new ArrayList<Garcom>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao;


        do {
            // TODO: Deixar o menu bonitinho :)
            System.out.println(" 1. Cadastro de mesa\n 2. Remoção de mesa\n 3. Busca mesa pelo número\n 4. Busca mesa pela capacidade de clientes\n 5. relatório de mesas\n 0. sair\n");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o codigo da mesa: ");
                    int codigoMesa = sc.nextInt();

                    System.out.print("Numero da mesa: ");
                    int numeroMesa = sc.nextInt();

                    System.out.print("Situação da mesa: ");
                    String situacao = sc.next();

                    System.out.print("Capacidade de ocupação da mesa: ");
                    int capacidadeMaxima = sc.nextInt();

                    Mesa mesa1 = new Mesa(codigoMesa,numeroMesa,situacao,capacidadeMaxima);
                    gravaMesa(mesa1);

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

    private static void gravaMesa(Mesa mesa) {
        try {
            BD_Mesa.add(mesa);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao gravar mesa!");
        }
    }
}