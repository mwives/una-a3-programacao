package useCases.garcom;

import java.sql.SQLException;
import java.util.Scanner;

import domain.model.entities.Garcom;
import domain.model.enums.Genero;
import infra.database.repositories.GarconsRepository;

public class CadastrarGarcomUseCase {
  private final GarconsRepository garconsRepository;
  private final Scanner sc;

  public CadastrarGarcomUseCase(GarconsRepository garconsRepository, Scanner sc) {
    this.garconsRepository = garconsRepository;
    this.sc = sc;
  }

  public void handle() throws SQLException {
    System.out.print("\nDigite o nome: ");
    String nome = sc.nextLine();

    System.out.print("Digite a data de nascimento (formato yyyy-mm-dd): ");
    String dataNascimento = sc.nextLine();

    System.out.print("Digite o email: ");
    String email = sc.nextLine();

    System.out.print("Digite o telefone: ");
    String telefone = sc.nextLine();

    System.out.print("Digite o CPF: ");
    String cpf = sc.nextLine();

    Garcom garcomJaCadastrado = garconsRepository.findByCpf(cpf);

    if (garcomJaCadastrado != null) {
      System.out.println("\nGarçom com este CPF já cadastrado!");
      return;
    }

    System.out.println("Selecione uma opção de genero: ");
    System.out.println("+---------------------------------+");
    System.out.println("| 1. Masculino                    |");
    System.out.println("| 2. Feminino                     |");
    System.out.println("| 3. Outro                        |");
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
    }

    System.out.print("Digite o salario fixo: ");
    double salarioFixo = sc.nextDouble();

    Garcom garcom = new Garcom(nome, dataNascimento, email, telefone, cpf, sexo, salarioFixo);

    garconsRepository.create(garcom);

    System.out.println("\nGarçom cadastrado com sucesso!");
  }
}
