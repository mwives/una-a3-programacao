package useCases.garcom;

import java.sql.SQLException;
import java.util.List;

import domain.model.entities.Garcom;
import helpers.GarconsHelper;
import infra.database.repositories.GarconsRepository;

public class GerarRelatorioGarcomUseCase {
  private GarconsRepository garconsRepository;

  public GerarRelatorioGarcomUseCase() throws Exception {
    this.garconsRepository = new GarconsRepository();
  }

  public void handle() throws SQLException {
    List<Garcom> garcons = garconsRepository.findAll();

    if (garcons.isEmpty()) {
      System.out.println("\nNão há garçons cadastrados!");
      return;
    }

    for (Garcom garcom : garcons) {
      GarconsHelper.imprimirInformacoesGarcom(garcom);
    }
  }
}
