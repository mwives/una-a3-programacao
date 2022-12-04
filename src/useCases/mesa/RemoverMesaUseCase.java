package useCases.mesa;

import domain.model.entities.Mesa;
import helpers.MesasHelper;
import infra.database.repositories.MesasRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RemoverMesaUseCase {
    private MesasRepository mesasRepository;

    private Scanner sc;


    public RemoverMesaUseCase(MesasRepository mesasRepository, Scanner sc) {
        this.mesasRepository = mesasRepository;
        this.sc = sc;
    }

    public void handle() throws SQLException {
        List<Mesa> mesasCadastradas = this.mesasRepository.findAll();

        MesasHelper.listarNumeroMesas(mesasCadastradas);
        System.out.println("Digite o número da mesa: ");
        int numeroMesa = sc.nextInt();

        Mesa mesa = mesasRepository.findByNumeroMesa(numeroMesa);
        if (mesa != null) {
            mesasRepository.delete(mesa.getCodigoMesa());
            System.out.println("\nMesa removida com sucesso");

        } else {
            System.out.println("\nMesa não encontrada");
        }

        }
    }
