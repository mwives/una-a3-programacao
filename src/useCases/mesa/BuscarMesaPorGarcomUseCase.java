package useCases.mesa;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.model.entities.Garcom;
import domain.model.entities.Mesa;
import domain.model.enums.SituacaoMesa;
import helpers.GarconsHelper;
import helpers.MesasHelper;
import infra.database.repositories.GarconsRepository;
import infra.database.repositories.MesasRepository;

public class BuscarMesaPorGarcomUseCase {
  private final MesasRepository mesasRepository;
  private final GarconsRepository garconsRepository;
  private final Scanner sc;

  public BuscarMesaPorGarcomUseCase(MesasRepository mesasRepository, GarconsRepository garconsRepository, Scanner sc) {
    this.mesasRepository = mesasRepository;
    this.garconsRepository = garconsRepository;
    this.sc = sc;
  }

  public void handle() throws SQLException {
    List<Garcom> garconsCadastrados = garconsRepository.findAll();

    GarconsHelper.listarEmailGarconsCadastrados(garconsCadastrados);

    System.out.print("\nDigite o email do garçom: ");
    String emailGarcom = sc.next();

    Garcom garcom = this.garconsRepository.findByEmail(emailGarcom);

    if (garcom == null) {
      System.out.println("\nNenhum garçom encontrado para este email!");
      return;
    }

    List<Mesa> mesasGarcom = this.mesasRepository.findByGarcomId(garcom.getCodigoGarcom(), SituacaoMesa.OCUPADA);

    if (mesasGarcom.size() == 0) {
      System.out.println("\nNenhuma mesa ocupada encontrada para este garçom!");
      return;
    }

    System.out.println("\nMesas ocupadas do garçom " + garcom.getNome() + ":");

    for (Mesa mesa : mesasGarcom) {
      MesasHelper.imprimirInformacoesMesa(mesa);
    }
  }
}
