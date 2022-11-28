package useCases.garcom;

import java.sql.SQLException;
import java.util.Scanner;

import helpers.GarconsHelper;
import infra.database.repositories.GarconsRepository;
import model.entities.Garcom;

public class BuscarGarcomUseCase {
    GarconsRepository garconsRepository;

    public BuscarGarcomUseCase() throws Exception {
        this.garconsRepository = new GarconsRepository();
    }

    public void handle() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("+---------------------------------+");
        System.out.println("| 1. Por CPF                      |");
        System.out.println("| 2. Por Email                    |");
        System.out.println("+---------------------------------+");

        System.out.print("Digite a opção desejada: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                opcaoBuscarGarcomPorCpf();
                break;
            case 2:
                buscarGarcomPorEmail();
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;
        }

        sc.close();
    }

    private void opcaoBuscarGarcomPorCpf() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o CPF: ");
        String cpf = sc.nextLine();

        Garcom garcom = garconsRepository.findByCpf(cpf);

        if (garcom != null) {
            GarconsHelper.imprimirInformacoesGarcom(garcom);
        } else {
            System.out.println("\nGarçom não encontrado!");
        }

        sc.close();
    }

    private void buscarGarcomPorEmail() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nDigite o email: ");
        String email = sc.nextLine();

        Garcom garcom = garconsRepository.findByEmail(email);

        if (garcom != null) {
            GarconsHelper.imprimirInformacoesGarcom(garcom);
        } else {
            System.out.println("\nGarçom não encontrado!");
        }

        sc.close();
    }
}
