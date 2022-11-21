import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Mesa> BD_Mesa = new ArrayList<>();
    static int BD_Mesa_Auto_Increment = 1;

    static List<Garcom> BD_Garcom = new ArrayList<>();
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
            System.out.println("|     Opcoes de mesas             |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Cadastrar                    |");
            System.out.println("| 2. Atualizar situacao           |");
            System.out.println("| 3. Remover                      |");
            System.out.println("| 4. Buscar                       |");
            System.out.println("| 5. Gerar relatorio              |");
            System.out.println("| 0. Voltar                       |");
            System.out.println("+---------------------------------+");

            System.out.print("Digite a opcao desejada: ");
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
                    opcoesRelatoriosMesa();
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
        if (BD_Garcom.isEmpty()) {
            System.out.println("\nPara cadastrar uma mesa, é preciso cadastrar um garçom!");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o numero da mesa: ");
        int numero = sc.nextInt();

        System.out.print("Digite a capacidade da mesa: ");
        int capacidade = sc.nextInt();

        Garcom garcom;

        do {
            System.out.println("Garcons cadastrados:");

            for (Garcom g : BD_Garcom) {
                System.out.println("- " + g.getEmail());
            }

            System.out.print("Digite o email do garcom responsavel: ");
            String emailGarcom = sc.next();

            garcom = buscarGarcomPorEmail(emailGarcom);

            if (garcom == null) {
                System.out.println("Garcom não encontrado!");
            }
        } while (garcom == null);


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

        System.out.println("\nNenhuma mesa com este numero encontrada!");
    }

    public static void opcoesRelatoriosMesa() {
        Scanner sc = new Scanner(System.in);


        System.out.println("+---------------------------------+");
        System.out.println("| 1. Geral                        |");
        System.out.println("| 2. Por mesas livres             |");
        System.out.println("| 3. Por capacidade               |");
        System.out.println("| 4. Por garçom (mesas ocupadas)  |");
        System.out.println("+---------------------------------+");

        System.out.print("Escolha uma opcao p/ relatorio: ");
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
        System.out.println("\nID: " + mesa.getCodigoMesa());
        System.out.println("Numero: " + mesa.getNumeroMesa());
        System.out.println("Capacidade: " + mesa.getCapacidadeMaxima());
        System.out.println("Situacao: " + mesa.getSituacao());
        System.out.println("Garçom responsável: " + mesa.getGarcomResponsavel().getNome());
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

        do {
            System.out.println("\nGarcons cadastrados:");

            for (Garcom g : BD_Garcom) {
                System.out.println("- " + g.getEmail());
            }

            System.out.print("Digite o email do garcom: ");
            String emailGarcom = sc.next();

            garcom = buscarGarcomPorEmail(emailGarcom);

            if (garcom == null) {
                System.out.println("\n  Garcom não encontrado!");
            }
        } while (garcom == null);

        boolean mesaEncontrada = false;

        for (Mesa mesa : BD_Mesa) {
            boolean garcomResponsavelPelaMesa = mesa.getGarcomResponsavel().equals(garcom);
            boolean mesaOcupada = mesa.getSituacao() == SituacaoMesa.OCUPADA;

            if (garcomResponsavelPelaMesa && mesaOcupada) {
                imprimirInformacoesMesa(mesa);
                mesaEncontrada = true;
            }
        }

        if (!mesaEncontrada) {
            System.out.println("\nNenhuma mesa encontrada para esse garçom!");
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

        System.out.print("\nDigite o nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite a data de nascimento: ");
        String dataNascimento = sc.nextLine();

        System.out.print("Digite o email: ");
        String email = sc.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();

        System.out.println("Selecione uma opção de genero: ");
        System.out.println("+---------------------------------+");
        System.out.println("| 1. Masculino                    |");
        System.out.println("| 2. Feminino                     |");
        System.out.println("| 2. Outro                        |");
        System.out.println("+---------------------------------+");

        int opcaoSexo = sc.nextInt();

        Genero sexo;
        
        switch (opcaoSexo) {
            case 1:
                sexo = Genero.MASCULINO;
                break;
            case 2:
                sexo = Genero.FEMININO;
                break;
            default:
                sexo = Genero.OUTRO;
                break;
        };

        System.out.print("Digite o salario fixo: ");
        double salarioFixo = sc.nextDouble();

        Garcom garcom = new Garcom(BD_Garcom_Auto_Increment, nome, dataNascimento, email, telefone, cpf, sexo, salarioFixo);

        BD_Garcom.add(garcom);
        BD_Garcom_Auto_Increment++;

        System.out.println("Garçom cadastrado com sucesso!");
    }

    public static void imprimirInformacoesGarcom(Garcom garcom) {
        System.out.println("\nID: " + garcom.getCodigoGarcom());
        System.out.println("Nome: " + garcom.getNome());
        System.out.println("Data de nascimento: " + garcom.getDataNascimento());
        System.out.println("Email: " + garcom.getEmail());
        System.out.println("Telefone: " + garcom.getTelefone());
        System.out.println("CPF: " + garcom.getCpf());
        System.out.println("Sexo: " + garcom.getSexo());
        System.out.println("Salario fixo: " + garcom.getSalariofixo());
        System.out.println("Responsável por: " + getQuantidadeMesasResponsavel(garcom) + " mesa(s)");
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
        Scanner sc = new Scanner(System.in);

        System.out.println("+---------------------------------+");
        System.out.println("| 1. Por CPF                      |");
        System.out.println("| 2. Por Email                    |");
        System.out.println("+---------------------------------+");

        System.out.print("Digite a opção desejada: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                opcaoBuscarGarcomPorCpf();
                break;
            case 2:
                opcaoBuscarGarcomPorEmail();
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;
        }
    }

    public static void opcaoBuscarGarcomPorCpf() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o CPF: ");
        String cpf = sc.nextLine();

        Garcom garcom = buscarGarcomPorCpf(cpf);

        if (garcom != null) {
            imprimirInformacoesGarcom(garcom);
            return;
        }
        
        System.out.println("\nGarçom não encontrado!");
    }

    public static Garcom buscarGarcomPorCpf(String cpf) {
        for (Garcom garcom : BD_Garcom) {
            if (garcom.getCpf().equals(cpf)) {
                return garcom;
            }
        }

        return null;
    }

    public static void opcaoBuscarGarcomPorEmail() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o email: ");
        String email = sc.nextLine();

        Garcom garcom = buscarGarcomPorEmail(email);

        if (garcom != null) {
            imprimirInformacoesGarcom(garcom);
            return;
        }
        
        System.out.println("\nGarçom não encontrado!");
    }

    public static Garcom buscarGarcomPorEmail(String email) {
        for (Garcom garcom : BD_Garcom) {
            if (garcom.getEmail().equals(email)) {
                return garcom;
            }
        }

        return null;
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
        for (Garcom garcom : BD_Garcom) {
            imprimirInformacoesGarcom(garcom);
        }
    }

    private static int getQuantidadeMesasResponsavel(Garcom garcom) {
        int quantidadeMesas = 0;

        for (Mesa mesa : BD_Mesa) {
            if (mesa.getGarcomResponsavel() == garcom) {
                quantidadeMesas++;
            }
        }

        return quantidadeMesas;
    }
}
