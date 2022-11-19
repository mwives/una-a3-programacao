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
            System.out.println("| 1. Opcoes p/ garcom             |");
            System.out.println("| 2. Opcoes p/ mesas              |");
            System.out.println("| 0. Sair                         |");
            System.out.println("+---------------------------------+");

            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    opcoesGarcom();
                    break;
                case 2:
                    opcoesMesas();
                    break;
                case 0:
                    System.out.println("Tchau :(");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // Mesa
    public static void opcoesMesas() {
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println();
            System.out.println("+---------------------------------+");
            System.out.println("|     Opções de mesas             |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Cadastrar                    |");
            System.out.println("| 2. Atualizar situacao           |");
            System.out.println("| 3. Remover                      |");
            System.out.println("| 4. Buscar pelo numero           |");
            System.out.println("| 5. Buscar pela capacidade       |");
            System.out.println("| 6. Buscar mesas livres          |");
            System.out.println("| 7. Relatorio                    |");
            System.out.println("| 0. Voltar                       |");
            System.out.println("+---------------------------------+");

            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarMesa();
                    break;
                case 2:
                    atualizarSituacaoMesa();
                    break;
                case 3:
                    removerMesa();
                    break;
                case 4:
                    buscarMesaPeloNumero();
                    break;
                case 5:
                    buscarMesaPelaCapacidade();
                    break;
                case 6:
                    buscarMesasLivres();
                    break;
                case 7:
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

        System.out.print("Digite o CPF do garcom responsavel: ");
        String cpf = sc.next();

        Garcom garcom = buscarGarcomPorCpf(cpf);

        Mesa mesa = new Mesa(BD_Mesa_Auto_Increment, numero, capacidade, garcom);

        BD_Mesa.add(mesa);
        BD_Mesa_Auto_Increment++;

        System.out.println("Mesa cadastrada com sucesso!");
    }

    public static void atualizarSituacaoMesa() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o numero da mesa: ");
        int numero = sc.nextInt();

        Mesa mesaEncontrada = null;

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getNumeroMesa() == numero) {
                mesaEncontrada = mesa;
            }
        }

        System.out.println("+---------------------------------+");
        System.out.println("| 1. Livre                        |");
        System.out.println("| 2. Ocupada                      |");
        System.out.println("| 3. Reservada                    |");
        System.out.println("+---------------------------------+");

        System.out.print("Escolha uma opcao para a situacao: ");
        int situacao = sc.nextInt();

        switch (situacao) {
            case 1:
                mesaEncontrada.setSituacao(SituacaoMesa.LIVRE);
                break;
            case 2:
                mesaEncontrada.setSituacao(SituacaoMesa.OCUPADA);
                break;
            case 3:
                mesaEncontrada.setSituacao(SituacaoMesa.RESERVADA);
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;
        }
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

    public static void buscarMesasLivres() {
        // Adicionar o garçom responsável
        boolean mesaEncontrada = false;

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getSituacao() == SituacaoMesa.LIVRE) {
                imprimirInformacoesMesa(mesa);
                mesaEncontrada = true;
            }
        }

        if (!mesaEncontrada) {
            System.out.println("\nNenhuma mesa livre encontrada!");
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

    // Garcom
    public static void opcoesGarcom() {
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println();
            System.out.println("+---------------------------------+");
            System.out.println("|     Opções de mesas             |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Cadastrar garçom             |");
            System.out.println("| 2. Remover                      |");
            System.out.println("| 3. Buscar garçom                |");
            System.out.println("| 4. Relatório de garçons         |");
            System.out.println("| 0. Voltar                       |");
            System.out.println("+---------------------------------+");

            // Buscar garçom tanto por email quanto cpf

            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarGarcom();
                    break;
                case 2:
                    removerGarcom();
                    break;
                case 3:
                    buscarGarcom();
                    break;
                case 4:
                    relatorioGarcom();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void cadastrarGarcom() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do garçom: ");
        String nome = sc.nextLine();

        System.out.print("Digite o CPF do garçom: ");
        String cpf = sc.nextLine();

        System.out.print("Digite o email do garçom: ");
        String email = sc.nextLine();

        System.out.print("Digite o telefone do garçom: ");
        String telefone = sc.nextLine();

        Garcom garcom = new Garcom(nome, cpf, email, telefone);

        BD_Garcom.add(garcom);

        System.out.println("Garçom cadastrado com sucesso!");
    }

    public static void removerGarcom() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o CPF do garçom: ");
        String cpf = sc.nextLine();

        for (Garcom garcom : BD_Garcom) {
            if (garcom.getCpf().equals(cpf)) {
                BD_Garcom.remove(garcom);
                System.out.println("Garçom removido com sucesso!");
                return;
            }
        }

        System.out.println("Garçom não encontrado!");
    }

    public static void buscarGarcom() {
        // dar opções de buscar por cpf ou email
        Scanner sc = new Scanner(System.in);

        System.out.println("+---------------------------------+");
        System.out.println("| 1. Por CPF                      |");
        System.out.println("| 2. Por Email                    |");
        System.out.println("+---------------------------------+");

        System.out.print("Digite a opção desejada: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                buscarGarcomPorCpf();
                break;
            case 2:
                buscarGarcomPorEmail();
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;
        }
    }

    public static void buscarGarcomPorCpf() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o CPF do garçom: ");
        String cpf = sc.nextLine();

        for (Garcom garcom : BD_Garcom) {
            if (garcom.getCpf().equals(cpf)) {
                imprimirInformacoesGarcom(garcom);
                return;
            }
        }

        System.out.println("Garçom não encontrado!");
    }

    public static void buscarGarcomPorEmail() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o email do garçom: ");
        String email = sc.nextLine();

        for (Garcom garcom : BD_Garcom) {
            if (garcom.getEmail().equals(email)) {
                imprimirInformacoesGarcom(garcom);
                return;
            }
        }

        System.out.println("Garçom não encontrado!");
    }

    public static List<Mesa> buscarMesasDoGarcom(Garcom garcom) {
        List<Mesa> mesas = new ArrayList<>();

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getGarcomResponsavel() == garcom) {
                mesas.add(mesa);
            }
        }

        return mesas;
    }

    public static void relatorioGarcom() {
        System.out.println("Relatório de garçons...");

        for (Garcom garcom : BD_Garcom) {
            imprimirInformacoesGarcom(garcom);

            List<Mesa> mesas = buscarMesasDoGarcom(garcom);

            if (mesas.size() > 0) {
                System.out.println("Mesas que o garçom está responsável: ");

                for (Mesa mesa : mesas) {
                    System.out.println("Mesa " + mesa.getNumeroMesa());
                }
            } else {
                System.out.println("Garçom não está responsável por nenhuma mesa!");
            }
        }
    }
}
