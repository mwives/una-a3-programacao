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

  public void create(Mesa mesa) {
    try {
      String sql = "INSERT INTO mesas (numero_mesa, capacidade_maxima, codigo_garcom) VALUES (?, ?, ?);";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setInt(1, mesa.getNumeroMesa());
      statement.setInt(2, mesa.getCapacidadeMaxima());
      statement.setInt(3, mesa.getGarcomResponsavel().getCodigoGarcom());

      statement.execute();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao criar mesa");
    }
  }

  public List<Mesa> findAll() {
    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom;";

      PreparedStatement statement = connection.prepareStatement(sql);

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
    }

    return null;
  }

  public Mesa findByNumeroMesa(int numeroMesa) {
    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom WHERE mesas.numero_mesa = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setInt(1, numeroMesa);

      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Mesa mesa = mapMesa(resultSet);
        return mesa;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao buscar mesa");
    }

    return null;
  }

  public List<Mesa> findMesasLivres() {
    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom WHERE mesas.situacao = 'LIVRE';";

      PreparedStatement statement = connection.prepareStatement(sql);

      ResultSet resultSet = statement.executeQuery();

      List<Mesa> mesas = new ArrayList<>();

      while (resultSet.next()) {
        Mesa mesa = mapMesa(resultSet);
        mesas.add(mesa);
      }

      return mesas;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao listar mesas livres");
    }

    return null;
  }

  public List<Mesa> findByCapacidadeMaxima(int capacidadeMaxima) {
    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom WHERE mesas.capacidade_maxima = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setInt(1, capacidadeMaxima);

      ResultSet resultSet = statement.executeQuery();

      List<Mesa> mesas = new ArrayList<>();

      while (resultSet.next()) {
        Mesa mesa = mapMesa(resultSet);
        mesas.add(mesa);
      }

      return mesas;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao listar mesas por capacidade máxima");
    }

    return null;
  }

  public List<Mesa> findByGarcomId(int garcomId) {
    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom WHERE mesas.codigo_garcom = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

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
    }

    return null;
  }

  public List<Mesa> findByGarcomId(int garcomId, SituacaoMesa situacaoMesa) {
    try {
      String sql = "SELECT mesas.*, garcons.* FROM mesas INNER JOIN garcons ON mesas.codigo_garcom = garcons.codigo_garcom WHERE mesas.codigo_garcom = ? AND mesas.situacao = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

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

  public void update(Mesa mesa) {
    try {
      String sql = "UPDATE mesas SET numero_mesa = ?, capacidade_maxima = ?, codigo_garcom = ? WHERE codigo_mesa = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setInt(1, mesa.getNumeroMesa());
      statement.setInt(2, mesa.getCapacidadeMaxima());
      statement.setInt(3, mesa.getGarcomResponsavel().getCodigoGarcom());
      statement.setInt(4, mesa.getCodigoMesa());

      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao atualizar mesa");
    }
  }

  public void delete(int codigoMesa) {
    try {
      String sql = "DELETE FROM mesas WHERE codigo_mesa = ?;";

      PreparedStatement statement = connection.prepareStatement(sql);

      statement.setInt(1, codigoMesa);

      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error ao deletar mesa");
    }
  }
}
