package useCases.mesa;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.model.entities.Mesa;
import helpers.MesasHelper;
import infra.database.repositories.MesasRepository;

public class BuscarMesaPelaCapacidadeUseCase {
  private MesasRepository mesasRepository;
  private Scanner sc;

  public BuscarMesaPelaCapacidadeUseCase(MesasRepository mesasRepository, Scanner sc) {
    this.mesasRepository = mesasRepository;
    this.sc = sc;
  }

  public void handle() throws SQLException {
    System.out.print("Digite a capacidade mínima da mesa: ");
    int capacidadeMinima = sc.nextInt();

    List<Mesa> mesas = mesasRepository.findByCapacidade(capacidadeMinima);

    if (mesas.size() == 0) {
      System.out.println("\nNenhuma mesa encontrada para essa capacidade!");
      return;
    }

    System.out.println("Mesas com capacidade >= " + capacidadeMinima + ": ");

    for (Mesa mesa : mesas) {
      MesasHelper.imprimirInformacoesMesa(mesa);
    }
  }
}
