package useCases.garcom;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.model.entities.Garcom;
import helpers.GarconsHelper;
import infra.database.repositories.GarconsRepository;

public class RemoverGarcomUseCase {
  private final GarconsRepository garconsRepository;
  private final Scanner sc;

  public RemoverGarcomUseCase(GarconsRepository garconsRepository, Scanner sc) {
    this.garconsRepository = garconsRepository;
    this.sc = sc;
  }

  public void handle() throws SQLException {
    List<Garcom> garconsCadastrados = this.garconsRepository.findAll();

    GarconsHelper.listarCpfGarconsCadastrados(garconsCadastrados);

    System.out.print("Digite o CPF do garçom: ");
    String cpf = sc.nextLine();

    Garcom garcom = garconsRepository.findByCpf(cpf);

    if (garcom != null) {
      garconsRepository.delete(garcom.getCodigoGarcom());
      System.out.println("\nGarçom removido com sucesso!");
    } else {
      System.out.println("\nGarçom não encontrado!");
    }
  }
}
