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
            System.out.println("| 1. Opcoes p/ mesas              |");
            System.out.println("| 2. Opcoes p/ garcom             |");
            System.out.println("| 0. Sair                         |");
            System.out.println("+---------------------------------+");

            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    opcoesMesas();
                    break;
                case 2:
                    System.out.println("TODO: Mostrar opções garçom");
                    break;
                case 0:
                    System.out.println("Tchau :(");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void opcoesMesas() {
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println();
            System.out.println("+---------------------------------+");
            System.out.println("|     Opções de mesas             |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Cadastrar                    |");
            System.out.println("| 2. Remover                      |");
            System.out.println("| 3. Buscar pelo numero           |");
            System.out.println("| 4. Buscar pela capacidade       |");
            System.out.println("| 5. Relatorio                    |");
            System.out.println("| 0. Voltar                       |");
            System.out.println("+---------------------------------+");

            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarMesa();
                    break;
                case 2:
                    removerMesa();
                    break;
                case 3:
                    buscarMesaPeloNumero();
                    break;
                case 4:
                    buscarMesaPelaCapacidade();
                    break;
                case 5:
                    relatorioMesas();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void cadastrarMesa() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Cadastrando mesa...");

        System.out.print("Digite o numero da mesa: ");
        int numero = sc.nextInt();

        System.out.print("Digite a capacidade da mesa: ");
        int capacidade = sc.nextInt();

        Mesa mesa = new Mesa(BD_Mesa_Auto_Increment, numero, capacidade);

        BD_Mesa.add(mesa);
        BD_Mesa_Auto_Increment++;

        System.out.println("Mesa cadastrada com sucesso!");
    }

    public static void imprimirInformacoesMesa(Mesa mesa) {
        System.out.println("\nID: " + mesa.getCodigoMesa());
        System.out.println("Numero: " + mesa.getNumeroMesa());
        System.out.println("Capacidade: " + mesa.getCapacidadeMaxima());
        System.out.println("Situacao: " + mesa.getSituacao());
    }

    public static void buscarMesaPeloNumero() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o numero da mesa: ");
        int numero = sc.nextInt();

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getNumeroMesa() == numero) {
                imprimirInformacoesMesa(mesa);
                return;
            }
        }

        System.out.println("Mesa não encontrada!");
    }

    public static void buscarMesaPelaCapacidade() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a capacidade da mesa: ");
        int capacidade = sc.nextInt();

        boolean mesaEncontrada = false;

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getCapacidadeMaxima() >= capacidade) {
                imprimirInformacoesMesa(mesa);
                mesaEncontrada = true;
            }
        }

        if (!mesaEncontrada) {
            System.out.println("\nNenhuma mesa encontrada para essa capacidade!");
        }
    }

    public static void removerMesa() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o numero da mesa: ");
        int numero = sc.nextInt();

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getNumeroMesa() == numero) {
                BD_Mesa.remove(mesa);
                System.out.println("Mesa removida com sucesso!");
                return;
            }
        }

        System.out.println("Mesa não encontrada!");
    }

    public static void relatorioMesas() {
        System.out.println("Relatório de mesas...");

        for (Mesa mesa : BD_Mesa) {
            imprimirInformacoesMesa(mesa);
        }
    }
}
