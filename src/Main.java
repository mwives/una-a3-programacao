import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Mesa> BD_Mesa = new ArrayList<Mesa>();
    static int BD_Mesa_Auto_Increment = 1;

    static List<Garcom> BD_Garcom = new ArrayList<Garcom>();
    static int BD_Garcom_Auto_Increment = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("+---------------------------------+");
            System.out.println("|     Menu de opções              |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Cadastro de mesa             |");
            System.out.println("| 2. Remoção de mesa              |");
            System.out.println("| 3. Busca mesa pelo número       |");
            System.out.println("| 4. Busca mesa pela capacidade   |");
            System.out.println("| 5. Relatório de mesas           |");
            System.out.println("| 0. Sair                         |");
            System.out.println("+---------------------------------+");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("\nNumero da mesa: ");
                    int numeroMesa = sc.nextInt();

                    System.out.print("Situação da mesa: ");
                    String situacao = sc.next();

                    System.out.print("Capacidade de ocupação da mesa: ");
                    int capacidadeMaxima = sc.nextInt();

                    Mesa mesa = new Mesa(BD_Mesa_Auto_Increment, numeroMesa, situacao, capacidadeMaxima);
                    gravaMesa(mesa);

                    break;
                case 2:
                    System.out.print("\nDigite codigo da mesa: ");
                    int codigoMesa = sc.nextInt();

                    removeMesa(codigoMesa);

                    break;
                case 3:
                    System.out.print("\nDigite o numero da mesa: ");
                    numeroMesa = sc.nextInt();

                    Mesa mesaEncontrada = encontraMesaPeloNumero(numeroMesa);

                    if (mesaEncontrada == null) {
                        System.out.println("Mesa nao encontrada");
                        break;
                    }

                    imprimeInformacoesMesa(mesaEncontrada);

                    break;
                case 4:
                    System.out.println("Mesa encontrada ");
                    break;
                case 5:
                    for (int i = 0; i < BD_Mesa.size(); i++) {
                        imprimeInformacoesMesa(BD_Mesa.get(i));
                    }

                    System.out.println();

                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida");
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }

    private static void gravaMesa(Mesa mesa) {
        try {
            BD_Mesa.add(mesa);
            BD_Mesa_Auto_Increment++;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao gravar mesa!");
        }
    }

    private static Mesa encontraMesaPeloNumero(int numeroMesa) {
        Mesa mesaEncontrada = null;

        try {
            for (int i = 0; i < BD_Mesa.size(); i++) {
                Mesa mesaAtual = BD_Mesa.get(i);

                if (mesaAtual.getNumeroMesa() == numeroMesa) {
                    mesaEncontrada = mesaAtual;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao encontrar mesa!");
        }

        return mesaEncontrada;
    }

    private static void imprimeInformacoesMesa(Mesa mesa) {
        System.out.printf("\nCodigo: %d\n", mesa.getCodigoMesa());
        System.out.printf("Numero: %d\n", mesa.getNumeroMesa());
        System.out.printf("Situacao: %s\n", mesa.getSituacao());
        System.out.printf("Capacidade Maxima: %d\n", mesa.getCapacidadeMaxima());
    }

    private static void removeMesa(int codigoMesa) {
        try {
            for (int i = 0; i < BD_Mesa.size(); i++) {
                Mesa mesaAtual = BD_Mesa.get(i);

                if (mesaAtual.getCodigoMesa() == codigoMesa) {
                    BD_Mesa.remove(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nErro ao remover mesa!");
        }
    }
}
