package useCases.mesa;

import domain.model.entities.Garcom;
import domain.model.entities.Mesa;
import helpers.GarconsHelper;
import helpers.MesasHelper;
import infra.database.repositories.GarconsRepository;
import infra.database.repositories.MesasRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AtualizarGarcomResponsavelUseCase {

    private MesasRepository mesasRepository;

    private GarconsRepository garconsRepository;

    private Scanner sc;

    public AtualizarGarcomResponsavelUseCase(GarconsRepository garconsRepository, MesasRepository mesasRepository, Scanner sc){
        this.garconsRepository = garconsRepository;
        this.sc = sc;
        this.mesasRepository = mesasRepository;
    }

    public void handle() throws SQLException {
        List <Mesa> mesacadastradas = this.mesasRepository.findAll();
        for (Mesa mesa : mesacadastradas){
            MesasHelper.imprimirInformacoesMesa(mesa);
        }
        System.out.println("\nDigite o número da mesa que deseja alterar o garcom responsável: ");
        int numeroMesa = sc.nextInt();

        Mesa mesa = mesasRepository.findByNumeroMesa(numeroMesa);
        if (mesa == null){
            System.out.println("Número da mesa não encontrado");
            return;
        }

        List<Garcom> garcomcadastrados = this.garconsRepository.findAll();
        for (Garcom garcom: garcomcadastrados) {
            GarconsHelper.imprimirInformacoesGarcom(garcom);
        }
        System.out.println("\nDigite o ID do garcom responsável que deseja atualizar: ");
        int codigoGarcom = sc.nextInt();

        Garcom garcom = garconsRepository.findByCodigoGarcom(codigoGarcom);




         if (garcom == null){
             System.out.println("Erro ao atualizar garcom");
             return;
         }
        mesasRepository.updateGarcomResponsavel(codigoGarcom,mesa.getCodigoMesa());
        System.out.println("\nGarcom responsável atualizado!");


        }


    }


