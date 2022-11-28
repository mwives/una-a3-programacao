package infra.database.repositories;

import infra.database.connection.DbConnection;
import model.entities.Garcom;
import model.enums.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GarconsRepository {
  private Connection connection;

  public GarconsRepository() {
    this.connection = DbConnection.getInstance();
  }

  public void create(Garcom garcom) {
    try {
      String sql = "INSERT INTO garcons (nome, data_nascimento, email, telefone, cpf, sexo, salario_fixo) VALUES (?, ?, ?, ?, ?, ?, ?);";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setString(1, garcom.getNome());
      statement.setString(2, garcom.getDataNascimento());
      statement.setString(3, garcom.getEmail());
      statement.setString(4, garcom.getTelefone());
      statement.setString(5, garcom.getCpf());
      statement.setString(6, garcom.getSexo().toString());
      statement.setDouble(7, garcom.getSalariofixo());

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao criar garçom");
    }
  }

  public List<Garcom> findAll() {
    try {
      String sql = "SELECT * FROM garcons;";

      PreparedStatement statement = connection.prepareStatement(sql);

      ResultSet resultSet = statement.executeQuery();

      List<Garcom> garcons = new ArrayList<>();

      while (resultSet.next()) {
        Garcom garcom = mapGarcom(resultSet);
        garcons.add(garcom);
      }

      return garcons;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao listar garçons");
    }

    return null;
  }

  public Garcom findByCpf(String cpf) {
    try {
      String sql = "SELECT * FROM garcons WHERE cpf = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setString(1, cpf);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        Garcom garcomEncontrado = mapGarcom(result);
        return garcomEncontrado;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao buscar garçom");
    }

    return null;
  }

  public Garcom findByEmail(String email) {
    try {
      String sql = "SELECT * FROM garcons WHERE email = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setString(1, email);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        Garcom garcomEncontrado = mapGarcom(result);
        return garcomEncontrado;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao buscar garçom");
    }

    return null;
  }

  private Garcom mapGarcom(ResultSet result) throws SQLException {
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

  public int countGarcons() {
    try {
      String sql = "SELECT COUNT(*) AS total_garcons FROM garcons;";

      PreparedStatement statement = connection.prepareStatement(sql);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        return result.getInt("total_garcons");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao contar garçons");
    }

    return 0;
  }

  public int countMesasResponsavel(int codigoGarcom) {
    try {
      String sql = "SELECT COUNT(*) AS total_mesas FROM mesas WHERE codigo_garcom = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setInt(1, codigoGarcom);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        return result.getInt("total_mesas");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao buscar garçom");
    }

    return 0;
  }

  public void update(Garcom garcom) {
    try {
      String sql = "UPDATE garcons SET nome = ?, data_nascimento = ?, email = ?, telefone = ?, cpf = ?, sexo = ?, salario_fixo = ? WHERE codigo_garcom = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setString(1, garcom.getNome());
      statement.setString(2, garcom.getDataNascimento());
      statement.setString(3, garcom.getEmail());
      statement.setString(4, garcom.getTelefone());
      statement.setString(5, garcom.getCpf());
      statement.setString(6, garcom.getSexo().toString());
      statement.setDouble(7, garcom.getSalariofixo());
      statement.setInt(8, garcom.getCodigoGarcom());

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao atualizar garçom");
    }
  }

  public void delete(int codigoGarcom) {
    try {
      String sql = "DELETE FROM garcons WHERE codigo_garcom = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setInt(1, codigoGarcom);

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao deletar garçom");
    }
  }
}
