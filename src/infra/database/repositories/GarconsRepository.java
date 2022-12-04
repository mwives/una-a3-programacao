package infra.database.repositories;

import infra.database.connection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.model.entities.Garcom;
import helpers.GarconsHelper;

public class GarconsRepository {
  private Connection connection;

  public GarconsRepository() throws Exception {
    this.connection = DbConnection.getInstance();
  }

  public void create(Garcom garcom) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "INSERT INTO garcons (nome, data_nascimento, email, telefone, cpf, sexo, salario_fixo) VALUES (?, ?, ?, ?, ?, ?, ?);";

      statement = connection.prepareStatement(sql);

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
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }

  public List<Garcom> findAll() throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT * FROM garcons;";

      statement = connection.prepareStatement(sql);

      ResultSet resultSet = statement.executeQuery();

      List<Garcom> garcons = new ArrayList<>();

      while (resultSet.next()) {
        Garcom garcom = GarconsHelper.mapResultSetGarcom(resultSet);
        garcons.add(garcom);
      }

      return garcons;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao listar garçons");
    } finally {
      statement.close();
    }

    return null;
  }

  public Garcom findByCodigoGarcom(int codigoGarcom) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT * FROM garcons WHERE codigo_garcom = ?;";

      statement = connection.prepareStatement(sql);

      statement.setInt(1, codigoGarcom);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        Garcom garcomEncontrado = GarconsHelper.mapResultSetGarcom(result);
        return garcomEncontrado;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao atualizar garçom responsável");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }

    return null;
  }

  public Garcom findByCpf(String cpf) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT * FROM garcons WHERE cpf = ?;";

      statement = connection.prepareStatement(sql);

      statement.setString(1, cpf);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        Garcom garcomEncontrado = GarconsHelper.mapResultSetGarcom(result);
        return garcomEncontrado;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao buscar garçom");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }

    return null;
  }

  public Garcom findByEmail(String email) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT * FROM garcons WHERE email = ?;";

      statement = connection.prepareStatement(sql);

      statement.setString(1, email);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        Garcom garcomEncontrado = GarconsHelper.mapResultSetGarcom(result);
        return garcomEncontrado;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao buscar garçom");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }

    return null;
  }

  public int countGarcons() throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT COUNT(*) AS total_garcons FROM garcons;";

      statement = connection.prepareStatement(sql);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        return result.getInt("total_garcons");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao contar garçons");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }

    return 0;
  }

  public int countMesasResponsavel(int codigoGarcom) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT COUNT(*) AS total_mesas FROM mesas WHERE codigo_garcom = ?;";

      statement = connection.prepareStatement(sql);

      statement.setInt(1, codigoGarcom);

      ResultSet result = statement.executeQuery();

      if (result.next()) {
        return result.getInt("total_mesas");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao buscar garçom");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }

    return 0;
  }

  public void update(Garcom garcom) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "UPDATE garcons SET nome = ?, data_nascimento = ?, email = ?, telefone = ?, cpf = ?, sexo = ?, salario_fixo = ? WHERE codigo_garcom = ?;";

      statement = connection.prepareStatement(sql);

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
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }

  public void delete(int codigoGarcom) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "DELETE FROM garcons WHERE codigo_garcom = ?;";

      statement = connection.prepareStatement(sql);

      statement.setInt(1, codigoGarcom);

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao deletar garçom");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }
}
