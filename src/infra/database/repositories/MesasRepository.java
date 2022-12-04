package infra.database.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.model.entities.Garcom;
import domain.model.entities.Mesa;
import domain.model.enums.SituacaoMesa;
import helpers.MesasHelper;
import infra.database.connection.DbConnection;

public class MesasRepository {
  private Connection connection;

  public MesasRepository() throws Exception {
    this.connection = DbConnection.getInstance();
  }

  public void create(Mesa mesa, Garcom garcom) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "INSERT INTO mesas (numero_mesa, situacao, capacidade_maxima, codigo_garcom) VALUES (?, ?, ?, ?);";
      statement = connection.prepareStatement(sql);

      statement.setInt(1, mesa.getNumeroMesa());
      statement.setString(2, mesa.getSituacao().toString());
      statement.setInt(3, mesa.getCapacidadeMaxima());
      statement.setInt(4, garcom.getCodigoGarcom());

      statement.execute();

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao criar mesa");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }

  public List<Mesa> findAll() throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom;";

      statement = connection.prepareStatement(sql);

      ResultSet resultSet = statement.executeQuery();

      List<Mesa> mesas = new ArrayList<>();

      while (resultSet.next()) {
        Mesa mesa = MesasHelper.mapResultSetMesa(resultSet);
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

  public Mesa findByNumeroMesa(int numeroMesa) throws SQLException {
    PreparedStatement statement = null;

    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom WHERE mesas.numero_mesa = ?;";

      statement = connection.prepareStatement(sql);

      statement.setInt(1, numeroMesa);

      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Mesa mesaEncontrada = MesasHelper.mapResultSetMesa(resultSet);
        return mesaEncontrada;
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao encontrar mesa");
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
        Mesa mesa = MesasHelper.mapResultSetMesa(resultSet);
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
        Mesa mesa = MesasHelper.mapResultSetMesa(resultSet);
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
  public void updateSituacao( SituacaoMesa situacaoMesa, int codigoMesa) throws SQLException {

    PreparedStatement statement = null;

    try {
      String sql = "UPDATE mesas SET situacao = ? WHERE codigo_mesa = ?;";

      statement = connection.prepareStatement(sql);

      statement.setString(1,situacaoMesa.toString());
      statement.setInt(2,codigoMesa);

      statement.execute();

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao atualizar situação mesa");
    } finally {
      if (statement != null){
        statement.close();
      }
    }
  }
  public void updateGarcomResponsavel(int codigoGarcom, int codigoMesa) throws SQLException {
    PreparedStatement statement = null ;

    try {
      String sql = "UPDATE mesas SET codigo_garcom = ? WHERE codigo_mesa = ?;" ;

      statement = connection.prepareStatement(sql);
      statement.setInt(1,codigoGarcom);
      statement.setInt(2,codigoMesa);


      statement.execute();


    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao atualizar garcom responsável da mesa");
    }finally {
      if (statement == null){
        statement.close();
      }
    }
  }

  public void delete(int codigoMesa) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "DELETE FROM mesas WHERE codigo_mesa = ?;";

      statement = connection.prepareStatement(sql);

      statement.setInt(1, codigoMesa);

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao deletar mesa");
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }
}
