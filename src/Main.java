import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" 1. Cadastro de mesa\n 2. Remoção de mesa\n 3. Busca mesa pelo número\n 4. Busca mesa pela capacidade de clientes\n 5. relatório de mesas\n 0. sair\n");
        int menu = sc.nextInt();


        switch (menu){

            case 1:

                System.out.println("informar o numero da mesa, a situação e a capacidade máxima");
                System.out.println("Cadastrar mesa");
                break ;

            case 2:

                System.out.println("Mesa removida");
                break ;

            case 3:

                System.out.println("Mesa encontrada");
                break;

            case 4:

                System.out.println("Mesa encontrada ");
                break;

            case 5:

                System.out.println("Relatorio das mesas");
                break;

            case 0:
                sc.close();
                break;








        }



        }

    }