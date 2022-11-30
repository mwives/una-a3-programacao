package infra.database.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.model.entities.Garcom;
import domain.model.entities.Mesa;
import domain.model.enums.Genero;
import domain.model.enums.SituacaoMesa;
import infra.database.connection.DbConnection;

public class MesasRepository {
  private Connection connection;

  public MesasRepository() throws Exception {
    this.connection = DbConnection.getInstance();
  }

  public List<Mesa> findAll() throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom;";

      statement = connection.prepareStatement(sql);

      ResultSet resultSet = statement.executeQuery();

      List<Mesa> mesas = new ArrayList<>();

      while (resultSet.next()) {
        Mesa mesa = mapMesa(resultSet);
        mesas.add(mesa);
      }

      return mesas;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao listar mesas");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }

    return null;
  }

  public List<Mesa> findByGarcomId(int garcomId) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom WHERE mesas.codigo_garcom = ?;";

      statement = connection.prepareStatement(sql);

      statement.setInt(1, garcomId);

      ResultSet resultSet = statement.executeQuery();

      List<Mesa> mesas = new ArrayList<>();

      while (resultSet.next()) {
        Mesa mesa = mapMesa(resultSet);
        mesas.add(mesa);
      }

      return mesas;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao listar mesas por garçom");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }

    return null;
  }

  public List<Mesa> findByGarcomId(int garcomId, SituacaoMesa situacaoMesa) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom WHERE mesas.codigo_garcom = ? AND mesas.situacao = ?;";

      statement = connection.prepareStatement(sql);

      statement.setInt(1, garcomId);
      statement.setString(2, situacaoMesa.toString());

      ResultSet resultSet = statement.executeQuery();

      List<Mesa> mesas = new ArrayList<>();

      while (resultSet.next()) {
        Mesa mesa = mapMesa(resultSet);
        mesas.add(mesa);
      }

      return mesas;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao listar mesas por garçom");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }

    return null;
  }

  public Mesa mapMesa(ResultSet result) throws SQLException {
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
