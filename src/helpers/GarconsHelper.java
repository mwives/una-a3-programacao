package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;

import domain.model.entities.Garcom;
import domain.model.enums.Genero;

public class GarconsHelper {
  public static void imprimirInformacoesGarcom(Garcom garcom) {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    System.out.println("\nID Garçom: " + garcom.getCodigoGarcom());
    System.out.println("Nome: " + garcom.getNome());
    System.out.println("Data de nascimento: " + garcom.getDataNascimento());
    System.out.println("Email: " + garcom.getEmail());
    System.out.println("Telefone: " + garcom.getTelefone());
    System.out.println("CPF: " + garcom.getCpf());
    System.out.println("Sexo: " + garcom.getSexo());
    System.out.println("Salario fixo: " + formatter.format(garcom.getSalariofixo()));
    System.out.println("Responsável por: " + garcom.getMesas().size() + " mesa(s)");
  }

  public static void listarEmailGarconsCadastrados(List<Garcom> garcons) {
    System.out.println("Email dos garçons cadastrados:");

    for (Garcom g : garcons) {
      System.out.println("- " + g.getEmail());
    }
  }

  public static void listarCpfGarconsCadastrados(List<Garcom> garcons) {
    System.out.println("CPF dos garçons cadastrados:");

    for (Garcom g : garcons) {
      System.out.println("- " + g.getCpf());
    }
  }

  public static Garcom mapResultSetGarcom(ResultSet result) throws SQLException {
    Genero sexo;

    if (result.getString("sexo").equals("MASCULINO")) {
      sexo = Genero.MASCULINO;
    } else if (result.getString("sexo").equals("FEMININO")) {
      sexo = Genero.FEMININO;
    } else {
      sexo = Genero.OUTRO;
    }

    Garcom garcom = new Garcom(
        result.getInt("codigo_garcom"),
        result.getString("nome"),
        result.getString("data_nascimento"),
        result.getString("email"),
        result.getString("telefone"),
        result.getString("cpf"),
        sexo,
        result.getDouble("salario_fixo"));

    return garcom;
  }
}
