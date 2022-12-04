package useCases.mesa;

import domain.model.entities.Mesa;
import domain.model.enums.SituacaoMesa;
import helpers.MesasHelper;
import infra.database.repositories.MesasRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AtualizarSituacaoMesaUseCase {
    private MesasRepository mesasRepository;
    private Scanner sc;

    public AtualizarSituacaoMesaUseCase(MesasRepository mesasRepository, Scanner sc) {
        this.mesasRepository = mesasRepository;
        this.sc = sc;
    }

    public void handle() throws SQLException {
        List<Mesa> mesascadastradas = this.mesasRepository.findAll();

        for (Mesa mesa : mesascadastradas) {
            MesasHelper.imprimirInformacoesMesa(mesa);
        }

        System.out.print("\nDigite o número da mesa que deseja atualizar a situação: ");
        int numeroMesa = sc.nextInt();

        Mesa mesa = mesasRepository.findByNumeroMesa(numeroMesa);

        if (mesa == null) {
            System.out.println("Mesa não encontrada");
            return;
        }

        System.out.println("+---------------------------------+");
        System.out.println("| 1. Livre                        |");
        System.out.println("| 2. Ocupada                      |");
        System.out.println("| 3. Reservada                    |");
        System.out.println("+---------------------------------+");

        System.out.print("Escolha uma opção para a situação: ");
        int situacao = sc.nextInt();

        switch (situacao) {
            case 1:
                assert mesa != null;
                mesa.setSituacao(SituacaoMesa.LIVRE);
                break;
            case 2:
                assert mesa != null;
                mesa.setSituacao(SituacaoMesa.OCUPADA);
                break;
            case 3:
                assert mesa != null;
                mesa.setSituacao(SituacaoMesa.RESERVADA);
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;

        }

        mesasRepository.updateSituacao(mesa.getSituacao(), mesa.getCodigoMesa());

        System.out.println("\nSituação da mesa atualizada!");
    }
}
