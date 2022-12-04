package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.model.entities.*;
import domain.model.enums.SituacaoMesa;
import infra.database.repositories.*;
import useCases.garcom.*;
import useCases.mesa.*;

public class Menu {
  // Garçons
  private final GarconsRepository garconsRepository;

  private final BuscarGarcomUseCase buscarGarcomUseCase;
  private final CadastrarGarcomUseCase cadastrarGarcomUseCase;
  private final GerarRelatorioGarcomUseCase gerarRelatorioGarcomUseCase;
  private final RemoverGarcomUseCase removerGarcomUseCase;

  private final BuscarMesaPelaCapacidadeUseCase buscarMesaPelaCapacidadeUseCase;
  private final BuscarMesaPeloNumeroUseCase buscarMesaPeloNumeroUseCase;
  private final BuscarMesaPorGarcomUseCase buscarMesaPorGarcomUseCase;
  private final BuscarMesasLivresUseCase buscarMesasLivresUseCase;
  private final BuscarTodasMesasUseCase buscarTodasMesasUseCase;
  private final CadastrarMesaUseCase cadastrarMesaUseCase;
  private final RemoverMesaUseCase removerMesaUseCase;

  private final AtualizarSituacaoMesaUseCase atualizarSituacaoMesaUseCase;

  private final AtualizarGarcomResponsavelUseCase atualizarGarcomResponsavelUseCase;


  // Antigo! Deve ser removido
  static List<Mesa> BD_Mesa = new ArrayList<>();

  public Menu() throws Exception {
    Scanner sc = new Scanner(System.in);

    // Garçons
    this.garconsRepository = new GarconsRepository();

    this.buscarGarcomUseCase = new BuscarGarcomUseCase(garconsRepository, sc);
    this.cadastrarGarcomUseCase = new CadastrarGarcomUseCase(garconsRepository, sc);
    this.gerarRelatorioGarcomUseCase = new GerarRelatorioGarcomUseCase(garconsRepository);
    this.removerGarcomUseCase = new RemoverGarcomUseCase(garconsRepository, sc);

    // Mesas
    // Mesas
    MesasRepository mesasRepository = new MesasRepository();

    this.buscarMesaPelaCapacidadeUseCase = new BuscarMesaPelaCapacidadeUseCase(mesasRepository, sc);
    this.buscarMesaPeloNumeroUseCase = new BuscarMesaPeloNumeroUseCase(mesasRepository, sc);
    this.buscarMesaPorGarcomUseCase = new BuscarMesaPorGarcomUseCase(mesasRepository, garconsRepository, sc);
    this.buscarMesasLivresUseCase = new BuscarMesasLivresUseCase(mesasRepository);
    this.buscarTodasMesasUseCase = new BuscarTodasMesasUseCase(mesasRepository);
    this.cadastrarMesaUseCase = new CadastrarMesaUseCase(mesasRepository, garconsRepository, sc);
    this.removerMesaUseCase = new RemoverMesaUseCase(mesasRepository,sc);
    this.atualizarSituacaoMesaUseCase = new AtualizarSituacaoMesaUseCase(mesasRepository,sc);
    this.atualizarGarcomResponsavelUseCase = new AtualizarGarcomResponsavelUseCase(garconsRepository,mesasRepository,sc);

  }

  public void show() throws SQLException {
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
          mostrarOpcoesGarcom(sc);
          break;
        case 2:
          mostrarOpcoesMesa(sc);
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

  private void mostrarOpcoesGarcom(Scanner sc) throws SQLException {
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

  private void mostrarOpcoesMesa(Scanner sc) throws SQLException {
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
          this.cadastrarMesaUseCase.handle();
          break;
        case 2:
          this.atualizarSituacaoMesaUseCase.handle();
          break;
        case 3:
          this.atualizarGarcomResponsavelUseCase.handle();
          break;
        case 4:
          this.removerMesaUseCase.handle();
          break;
        case 5:
          this.buscarMesaPeloNumeroUseCase.handle();
          break;
        case 6:
          opcoesRelatoriosMesa(sc);
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
  private void listarEmailGarconsCadastrados() throws SQLException {
    List<Garcom> garconsCadastrados = this.garconsRepository.findAll();

    System.out.println("Garçons cadastrados:");

    for (Garcom g : garconsCadastrados) {
      System.out.println("- " + g.getEmail());
    }
  }

  private void opcoesRelatoriosMesa(Scanner sc) throws SQLException {
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
        this.buscarTodasMesasUseCase.handle();
        break;
      case 2:
        this.buscarMesasLivresUseCase.handle();
        break;
      case 3:
        this.buscarMesaPelaCapacidadeUseCase.handle();
        break;
      case 4:
        this.buscarMesaPorGarcomUseCase.handle();
        break;
      default:
        System.out.println("\nOpção inválida!");
        break;
    }
  }
}
