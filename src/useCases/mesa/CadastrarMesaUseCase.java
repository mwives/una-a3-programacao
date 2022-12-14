package useCases.mesa;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import domain.model.entities.Garcom;
import domain.model.entities.Mesa;
import helpers.GarconsHelper;
import infra.database.repositories.GarconsRepository;
import infra.database.repositories.MesasRepository;

public class CadastrarMesaUseCase {
  private final MesasRepository mesasRepository;
  private final GarconsRepository garconsRepository;
  private final Scanner sc;

  public CadastrarMesaUseCase(MesasRepository mesasRepository, GarconsRepository garconsRepository, Scanner sc)
      throws Exception {
    this.mesasRepository = mesasRepository;
    this.garconsRepository = garconsRepository;
    this.sc = sc;
  }

  public void handle() throws SQLException {
    List<Garcom> garconsCadastrados = this.garconsRepository.findAll();

    if (garconsCadastrados.size() == 0) {
      System.out.println("\nPara cadastrar uma mesa, é preciso cadastrar um garçom!");
      return;
    }

    System.out.print("\nDigite o número da mesa: ");
    int numero = sc.nextInt();

    System.out.print("Digite a capacidade da mesa: ");
    int capacidade = sc.nextInt();

    GarconsHelper.listarEmailGarconsCadastrados(garconsCadastrados);

    System.out.print("\nDigite o email do garçom responsável: ");
    String emailGarcom = sc.next();

    Garcom garcom = this.garconsRepository.findByEmail(emailGarcom);

    if (garcom == null) {
      System.out.println("\nGarçom não encontrado!");
      sc.close();
      return;
    }

    Mesa mesa = new Mesa(numero, capacidade, garcom);

    this.mesasRepository.create(mesa, garcom);

    System.out.println("\nMesa cadastrada com sucesso!");
  }
}
