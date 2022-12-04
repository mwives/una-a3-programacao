package helpers;

import domain.model.entities.Garcom;
import domain.model.entities.Mesa;
import domain.model.enums.Genero;

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

    public static void imprimirInformaçoesMesa(Mesa mesa) {
        System.out.println("\nCódigo mesa: " + mesa.getCodigoMesa());
        System.out.println("Número mesa: " + mesa.getNumeroMesa());
        System.out.println("Situação mesa: " + mesa.getSituacao());
        System.out.println("Capacidade maxima: " + mesa.getCapacidadeMaxima());
        System.out.println("Nome do garçom responsável: " + mesa.getGarcomResponsavel().getNome());
    }

    public static Mesa mapResultSetMesa(ResultSet result) throws SQLException {
        Genero sexo;

        if (result.getString("sexo").equals("MASCULINO")) {
            sexo = Genero.MASCULINO;
        } else if (result.getString("sexo").equals("FEMININO")) {
            sexo = Genero.FEMININO;
        } else {
            sexo = Genero.OUTRO;
        }

        Garcom garcomResponsavel = new Garcom(
                result.getInt("codigo_garcom"),
                result.getString("nome"),
                result.getString("data_nascimento"),
                result.getString("email"),
                result.getString("telefone"),
                result.getString("cpf"),
                sexo,
                result.getDouble("salario_fixo"));

        Mesa mesa = new Mesa(
                result.getInt("codigo_mesa"),
                result.getInt("numero_mesa"),
                result.getInt("capacidade_maxima"),
                result.getInt("codigo_garcom"),
                garcomResponsavel);

        return mesa;
    }
}
