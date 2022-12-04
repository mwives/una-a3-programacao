package useCases.mesa;

import java.sql.SQLException;
import java.util.List;

import domain.model.entities.Mesa;
import helpers.MesasHelper;
import infra.database.repositories.MesasRepository;

public class BuscarTodasMesasUseCase {
  private MesasRepository mesasRepository;

  public BuscarTodasMesasUseCase(MesasRepository mesasRepository) {
    this.mesasRepository = mesasRepository;
  }

  public void handle() throws SQLException {
    List<Mesa> mesas = mesasRepository.findAll();

    if (mesas.size() == 0) {
      System.out.println("\nNenhuma mesa cadastrada!");
      return;
    }

    System.out.println("Mesas: ");

    for (Mesa mesa : mesas) {
      MesasHelper.imprimirInformacoesMesa(mesa);
    }
  }
}
