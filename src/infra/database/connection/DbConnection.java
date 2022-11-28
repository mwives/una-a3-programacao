package infra.database.connection;

import java.sql.*;

public class DbConnection {
    final String DB_URL = "jdbc:mysql://localhost:3306/restaurante";
    final String DB_USER = "root";
    final String DB_PASSWORD = "root";

    private static Connection connection;

    private DbConnection() throws Exception {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error ao conectar com o banco de dados");
        }
    }

    public static Connection getInstance() throws Exception {
        if (connection == null) {
            new DbConnection();
        }

        return connection;
    }

    // Usado para testar conexão com o banco
    public static void main(String[] args) throws Exception {
        Connection instance = getInstance();
        System.out.println(instance);
    }
}
