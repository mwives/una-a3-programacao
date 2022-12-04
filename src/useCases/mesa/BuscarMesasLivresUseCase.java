package useCases.mesa;

import java.sql.SQLException;
import java.util.List;

import domain.model.entities.Mesa;
import domain.model.enums.SituacaoMesa;
import helpers.MesasHelper;
import infra.database.repositories.MesasRepository;

public class BuscarMesasLivresUseCase {
  private final MesasRepository mesasRepository;

  public BuscarMesasLivresUseCase(MesasRepository mesasRepository) {
    this.mesasRepository = mesasRepository;
  }

  public void handle() throws SQLException {
    List<Mesa> mesasLivres = mesasRepository.findBySituacao(SituacaoMesa.LIVRE);

    if (mesasLivres.size() == 0) {
      System.out.println("\nNenhuma mesa livre encontrada!");
      return;
    }

    System.out.println("Mesas livres: ");

    for (Mesa mesa : mesasLivres) {
      MesasHelper.imprimirInformacoesMesa(mesa);
    }
  }
}
