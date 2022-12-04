package useCases.mesa;

import domain.model.entities.Mesa;
import helpers.MesasHelper;
import infra.database.repositories.MesasRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class BuscarMesaPeloNumeroUseCase {
    private final MesasRepository mesasRepository;
    private final Scanner sc;

    public BuscarMesaPeloNumeroUseCase(MesasRepository mesasRepository, Scanner sc) {
        this.mesasRepository = mesasRepository;
        this.sc = sc;
    }

    public void handle() throws SQLException {
        System.out.print("\nDigite o número da mesa: ");
        int numeroMesa = sc.nextInt();

        Mesa mesa = mesasRepository.findByNumeroMesa(numeroMesa);

        if (mesa != null) {
            MesasHelper.imprimirInformacoesMesa(mesa);
        } else {
            System.out.println("\nNenhuma mesa encontrada para este número!");
        }
    }
}
