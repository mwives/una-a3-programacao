package helpers;
import domain.model.entities.Mesa;
import java.util.List;
public class MesasHelper {
    public static void listarNumeroMesas (List<Mesa> mesas) {
        System.out.println("Mesas cadastradas: ");
        for (Mesa mesa: mesas ) {
            System.out.println("- " + mesa.getNumeroMesa());
        }
    }

    public static void imprimirInformaçoesMesa (Mesa mesa) {
        System.out.println("\nCodigo mesa: " + mesa.getCodigoMesa());
        System.out.println("Numero mesa: " + mesa.getNumeroMesa());
        System.out.println("Situaçao mesa: " + mesa.getSituacao());
        System.out.println("Capacidade maxima: " + mesa.getCapacidadeMaxima());
        System.out.println("Nome do garçom responsavel: " + mesa.getGarcomResponsavel().getNome());
    }
}
