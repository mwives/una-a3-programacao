package useCases.garcom;

import java.sql.SQLException;
import java.util.List;

import domain.model.entities.Garcom;
import helpers.GarconsHelper;
import infra.database.repositories.GarconsRepository;

public class GerarRelatorioGarcomUseCase {
  private final GarconsRepository garconsRepository;

  public GerarRelatorioGarcomUseCase(GarconsRepository garconsRepository) {
    this.garconsRepository = garconsRepository;
  }

  public void handle() throws SQLException {
    List<Garcom> garcons = garconsRepository.findAll();

    if (garcons.isEmpty()) {
      System.out.println("\nNão há garçons cadastrados!");
      return;
    }

    for (Garcom garcom : garcons) {
      int quantidadeMesasResponsavel = garconsRepository.countMesasGarcom(garcom.getCodigoGarcom());

      GarconsHelper.imprimirInformacoesGarcom(garcom, quantidadeMesasResponsavel);
    }
  }
}
