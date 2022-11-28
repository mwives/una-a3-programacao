import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import infra.database.repositories.GarconsRepository;
import model.entities.Garcom;
import model.entities.Mesa;
import model.enums.SituacaoMesa;
import useCases.garcom.BuscarGarcomUseCase;
import useCases.garcom.CadastrarGarcomUseCase;
import useCases.garcom.GerarRelatorioGarcomUseCase;
import useCases.garcom.RemoverGarcomUseCase;

/**
 * Ives Martins Watanabe / 202220670
 * Patrick Ferreira Rezende Dezuani / 202220304
 * Samuel Sales Coelho Lima / 202210219
 */

public class Main {
    // Garçons
    static GarconsRepository garconsRepository = new GarconsRepository();

    static BuscarGarcomUseCase buscarGarcomUseCase = new BuscarGarcomUseCase();
    static CadastrarGarcomUseCase cadastrarGarcomUseCase = new CadastrarGarcomUseCase();
    static GerarRelatorioGarcomUseCase gerarRelatorioGarcomUseCase = new GerarRelatorioGarcomUseCase();
    static RemoverGarcomUseCase removerGarcomUseCase = new RemoverGarcomUseCase();

    // Mesas
    static List<Mesa> BD_Mesa = new ArrayList<>();
    static int BD_Mesa_Auto_Increment = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("+---------------------------------+");
            System.out.println("|     Menu de opções              |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Opções p/ garçom             |");
            System.out.println("| 2. Opções p/ mesas              |");
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
                    System.out.println("\nTchau :(");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
                    break;
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
            System.out.println("| 2. Atualizar situação           |");
            System.out.println("| 3. Atualizar garçom responsável |");
            System.out.println("| 4. Remover                      |");
            System.out.println("| 5. Buscar                       |");
            System.out.println("| 6. Gerar relatório              |");
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
                    atualizarGarcomResponsavel();
                    break;
                case 4:
                    removerMesa();
                    break;
                case 5:
                    buscarMesaPeloNumero();
                    break;
                case 6:
                    opcoesRelatoriosMesa();
                    break;
                case 0:
                    System.out.println("\nVoltando...");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void cadastrarMesa() {
        int quantidadeGarconsCadastrados = garconsRepository.countGarcons();

        if (quantidadeGarconsCadastrados == 0) {
            System.out.println("\nPara cadastrar uma mesa, é preciso cadastrar um garçom!");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o número da mesa: ");
        int numero = sc.nextInt();

        System.out.print("Digite a capacidade da mesa: ");
        int capacidade = sc.nextInt();

        Garcom garcom;

        do {
            listarEmailGarconsCadastrados();

            System.out.print("\nDigite o email do garçom responsável: ");
            String emailGarcom = sc.next();

            garcom = garconsRepository.findByEmail(emailGarcom);

            if (garcom == null) {
                System.out.println("\nGarçom não encontrado!");
            }
        } while (garcom == null);

        Mesa mesa = new Mesa(numero, capacidade, garcom);

        BD_Mesa.add(mesa);
        BD_Mesa_Auto_Increment++;

        System.out.println("\nMesa cadastrada com sucesso!");
    }

    public static void atualizarSituacaoMesa() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o número da mesa: ");
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

        System.out.print("Escolha uma opção para a situação: ");
        int situacao = sc.nextInt();

        switch (situacao) {
            case 1:
                assert mesaEncontrada != null;
                mesaEncontrada.setSituacao(SituacaoMesa.LIVRE);
                break;
            case 2:
                assert mesaEncontrada != null;
                mesaEncontrada.setSituacao(SituacaoMesa.OCUPADA);
                break;
            case 3:
                assert mesaEncontrada != null;
                mesaEncontrada.setSituacao(SituacaoMesa.RESERVADA);
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;
        }
    }

    public static void atualizarGarcomResponsavel() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o número da mesa: ");
        int numero = sc.nextInt();

        Mesa mesaEncontrada = null;

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getNumeroMesa() == numero) {
                mesaEncontrada = mesa;
            }
        }

        if (mesaEncontrada == null) {
            System.out.println("Mesa não encontrada!");
            return;
        }

        Garcom garcom;

        do {
            listarEmailGarconsCadastrados();

            System.out.print("Digite o email do garçom responsável: ");
            String emailGarcom = sc.next();

            garcom = garconsRepository.findByEmail(emailGarcom);

            if (garcom == null) {
                System.out.println("\nGarçom não encontrado!");
            }
        } while (garcom == null);
    }

    public static void listarEmailGarconsCadastrados() {
        List<Garcom> garconsCadastrados = garconsRepository.findAll();

        System.out.println("Garçons cadastrados:");

        for (Garcom g : garconsCadastrados) {
            System.out.println("- " + g.getEmail());
        }
    }

    public static void removerMesa() {
        Scanner sc = new Scanner(System.in);

        for (Mesa m : BD_Mesa) {
            System.out.println("- " + m.getNumeroMesa());
        }

        System.out.print("\nDigite o número da mesa: ");
        int numero = sc.nextInt();

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getNumeroMesa() == numero) {
                BD_Mesa.remove(mesa);
                mesa.getGarcomResponsavel().removeMesa(mesa);

                System.out.println("\nMesa removida com sucesso!");

                return;
            }
        }

        System.out.println("Mesa não encontrada!");
    }

    public static void buscarMesaPeloNumero() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o número da mesa: ");
        int numero = sc.nextInt();

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getNumeroMesa() == numero) {
                imprimirInformacoesMesa(mesa);
                return;
            }
        }

        System.out.println("\nNenhuma mesa encontrada para este número!");
    }

    public static void opcoesRelatoriosMesa() {
        Scanner sc = new Scanner(System.in);

        System.out.println("+---------------------------------+");
        System.out.println("| 1. Geral                        |");
        System.out.println("| 2. Por mesas livres             |");
        System.out.println("| 3. Por capacidade               |");
        System.out.println("| 4. Por garçom (mesas ocupadas)  |");
        System.out.println("+---------------------------------+");

        System.out.print("Escolha uma opcao p/ relatório: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                relatorioGeralMesas();
                break;
            case 2:
                buscarMesasLivres();
                break;
            case 3:
                buscarMesaPelaCapacidade();
                break;
            case 4:
                buscarMesaPorGarcom();
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;
        }
    }

    public static void imprimirInformacoesMesa(Mesa mesa) {
        System.out.println("\nID Mesa: " + mesa.getCodigoMesa());
        System.out.println("Número: " + mesa.getNumeroMesa());
        System.out.println("Capacidade: " + mesa.getCapacidadeMaxima());
        System.out.println("Situação: " + mesa.getSituacao());
        System.out.println("Nome do garçom responsável: " + mesa.getGarcomResponsavel().getNome());
    }

    public static void relatorioGeralMesas() {
        for (Mesa mesa : BD_Mesa) {
            imprimirInformacoesMesa(mesa);
        }
    }

    public static void buscarMesasLivres() {
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

    public static void buscarMesaPorGarcom() {
        Scanner sc = new Scanner(System.in);

        Garcom garcom;

        listarEmailGarconsCadastrados();

        System.out.print("Digite o email do garçom: ");
        String emailGarcom = sc.next();

        garcom = garconsRepository.findByEmail(emailGarcom);

        if (garcom == null) {
            System.out.println("\nNenhuma mesa encontrada para este garçom!");
            return;
        }

        for (Mesa mesa : BD_Mesa) {
            boolean garcomResponsavelPelaMesa = mesa.getGarcomResponsavel().equals(garcom);
            boolean mesaOcupada = mesa.getSituacao() == SituacaoMesa.OCUPADA;

            if (garcomResponsavelPelaMesa && mesaOcupada) {
                imprimirInformacoesMesa(mesa);
            }
        }
    }

    // Garcom
    public static void opcoesGarcom() {
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println();
            System.out.println("+---------------------------------+");
            System.out.println("|     Opções de garçom            |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Cadastrar garçom             |");
            System.out.println("| 2. Remover                      |");
            System.out.println("| 3. Buscar garçom                |");
            System.out.println("| 4. Relatório de garçons         |");
            System.out.println("| 0. Voltar                       |");
            System.out.println("+---------------------------------+");

            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarGarcomUseCase.handle();
                    break;
                case 2:
                    removerGarcomUseCase.handle();
                    break;
                case 3:
                    buscarGarcomUseCase.handle();
                    break;
                case 4:
                    gerarRelatorioGarcomUseCase.handle();
                    break;
                case 0:
                    System.out.println("\nVoltando...");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}
