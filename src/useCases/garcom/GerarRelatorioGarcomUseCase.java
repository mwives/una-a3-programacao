package useCases.garcom;

import java.util.List;

import helpers.GarconsHelper;
import infra.database.repositories.GarconsRepository;
import model.entities.Garcom;

public class GerarRelatorioGarcomUseCase {
  private GarconsRepository garconsRepository;

  public GerarRelatorioGarcomUseCase() {
    this.garconsRepository = new GarconsRepository();
  }

  public void handle() {
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
