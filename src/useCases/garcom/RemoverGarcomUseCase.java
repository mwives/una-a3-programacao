package useCases.garcom;

import java.sql.SQLException;
import java.util.Scanner;

import domain.model.entities.Garcom;
import infra.database.repositories.GarconsRepository;

public class RemoverGarcomUseCase {
  private GarconsRepository garconsRepository;

  public RemoverGarcomUseCase() throws Exception {
    this.garconsRepository = new GarconsRepository();
  }

  public void handle() throws SQLException {
    Scanner sc = new Scanner(System.in);

    System.out.print("Digite o CPF do garçom: ");
    String cpf = sc.nextLine();

    Garcom garcom = garconsRepository.findByCpf(cpf);

    if (garcom != null) {
      garconsRepository.delete(garcom.getCodigoGarcom());
      System.out.println("\nGarçom removido com sucesso!");
    } else {
      System.out.println("\nGarçom não encontrado!");
    }

    sc.close();
  }
}
