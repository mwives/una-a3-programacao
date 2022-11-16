import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Mesa> BD_Mesa = new ArrayList<Mesa>();
    List<Garcom> BD_Garcom = new ArrayList<Garcom>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao;


        do {
            // TODO: Deixar o menu bonitinho :)
            System.out.println(" 1. Cadastro de mesa\n 2. Remoção de mesa\n 3. Busca mesa pelo número\n 4. Busca mesa pela capacidade de clientes\n 5. relatório de mesas\n 0. sair\n");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o codigo da mesa: ");
                    int codigoMesa = sc.nextInt();

                    System.out.print("Numero da mesa: ");
                    int numeroMesa = sc.nextInt();

                    System.out.print("Situação da mesa: ");
                    String situacao = sc.next();

                    System.out.print("Capacidade de ocupação da mesa: ");
                    int capacidadeMaxima = sc.nextInt();

                    Mesa mesa1 = new Mesa(codigoMesa, numeroMesa, situacao, capacidadeMaxima);
                    gravaMesa(mesa1);

                    break;
                case 2:
                    System.out.println("digite codigo mesa");
                    codigoMesa = sc.nextInt();
                    removeMesa (codigoMesa) ;



                    System.out.println("Mesa removida");
                    break;
                case 3:
                    System.out.print("Digite o numero da mesa: ");
                    numeroMesa = sc.nextInt();

                    Mesa mesaEncontrada = encontraMesa(numeroMesa);

                    if (mesaEncontrada == null) {
                        System.out.println("Mesa nao encontrada");
                        break;
                    }

                    System.out.println(mesaEncontrada.getCodigoMesa());
                    System.out.println(mesaEncontrada.getNumeroMesa());
                    System.out.println(mesaEncontrada.getSituacao());
                    System.out.println(mesaEncontrada.getCapacidadeMaxima());

                    break;
                case 4:
                    System.out.println("Mesa encontrada ");
                    break;
                case 5:
                    System.out.println("Relatorio das mesas");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }

    private static void gravaMesa(Mesa mesa) {
        try {
            BD_Mesa.add(mesa);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao gravar mesa!");
        }
    }

    private static Mesa encontraMesa(int numeroMesa) {
        Mesa mesaEncontrada = null;

        try {
            for (int i = 0; i < BD_Mesa.size(); i++) {
                Mesa mesaAtual = BD_Mesa.get(i);

                if (mesaAtual.getNumeroMesa() == numeroMesa) {
                    mesaEncontrada = mesaAtual;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao encontrar mesa!");
        }

        return mesaEncontrada;

    }

    private static void removeMesa(int codigoMesa){
        try {

            for (int i = 0; i < BD_Mesa.size(); i++) {
                Mesa mesaAtual = BD_Mesa.get(i);

                if (mesaAtual.getCodigoMesa() == codigoMesa) {
                    BD_Mesa.remove(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao remover mesa!");
        }

    }


    }
