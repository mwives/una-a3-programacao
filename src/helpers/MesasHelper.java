package helpers;

import domain.model.entities.Garcom;
import domain.model.entities.Mesa;
import domain.model.enums.SituacaoMesa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MesasHelper {
    public static void listarNumeroMesas(List<Mesa> mesas) {
        System.out.println("Mesas cadastradas: ");

        for (Mesa mesa : mesas) {
            System.out.println("- " + mesa.getNumeroMesa());
        }
    }

    public static void imprimirInformacoesMesa(Mesa mesa) {
        System.out.println("\nCódigo mesa: " + mesa.getCodigoMesa());
        System.out.println("Número mesa: " + mesa.getNumeroMesa());
        System.out.println("Situação mesa: " + mesa.getSituacao());
        System.out.println("Capacidade maxima: " + mesa.getCapacidadeMaxima());
        System.out.println("Nome do garçom responsável: " + mesa.getGarcomResponsavel().getNome());
    }

    public static Mesa mapResultSetMesa(ResultSet result) throws SQLException {
        Garcom garcom = GarconsHelper.mapResultSetGarcom(result);

        SituacaoMesa situacao;

        if (result.getString("situacao").equals("LIVRE")) {
            situacao = SituacaoMesa.LIVRE;
        } else if (result.getString("situacao").equals("OCUPADA")) {
            situacao = SituacaoMesa.OCUPADA;
        } else {
            situacao = SituacaoMesa.RESERVADA;
        }

        return new Mesa(
                result.getInt("codigo_mesa"),
                result.getInt("numero_mesa"),
                situacao,
                result.getInt("capacidade_maxima"),
                result.getInt("codigo_garcom"),
                garcom);
    }
}
