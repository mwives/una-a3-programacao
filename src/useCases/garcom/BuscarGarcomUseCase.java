package useCases.garcom;

import java.sql.SQLException;
import java.util.Scanner;

import domain.model.entities.Garcom;
import helpers.GarconsHelper;
import infra.database.repositories.GarconsRepository;

public class BuscarGarcomUseCase {
    GarconsRepository garconsRepository;

    public BuscarGarcomUseCase(GarconsRepository garconsRepository) throws Exception {
        this.garconsRepository = garconsRepository;
    }

    public void handle() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("+---------------------------------+");
        System.out.println("| 1. Por CPF                      |");
        System.out.println("| 2. Por Email                    |");
        System.out.println("+---------------------------------+");

        System.out.print("Digite a opção desejada: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                opcaoBuscarGarcomPorCpf(sc);
                break;
            case 2:
                buscarGarcomPorEmail(sc);
                break;
            default:
                System.out.println("\nOpção inválida!");
                break;
        }
    }

    private void opcaoBuscarGarcomPorCpf(Scanner sc) throws SQLException {
        System.out.print("\nDigite o CPF: ");
        String cpf = sc.nextLine();

        Garcom garcom = garconsRepository.findByCpf(cpf);

        if (garcom != null) {
            GarconsHelper.imprimirInformacoesGarcom(garcom);
        } else {
            System.out.println("\nGarçom não encontrado!");
        }
    }

    private void buscarGarcomPorEmail(Scanner sc) throws SQLException {
        System.out.print("\nDigite o email: ");
        String email = sc.nextLine();

        Garcom garcom = garconsRepository.findByEmail(email);

        if (garcom != null) {
            GarconsHelper.imprimirInformacoesGarcom(garcom);
        } else {
            System.out.println("\nGarçom não encontrado!");
        }
    }
}
