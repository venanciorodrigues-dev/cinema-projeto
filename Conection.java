package Pacote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conection {

    private static final String URL = "jdbc:postgresql://localhost:5432/CinemaDatabase";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Garante o carregamento do driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado: " + e.getMessage());
            throw new SQLException("Driver não encontrado", e);
        }
    }

    public static void main(String[] args) {
        try (Connection connection = conectar()) {
            if (connection != null) {
                JOptionPane.showMessageDialog(null, "✅ Conexão estabelecida com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Erro ao conectar: " + e.getMessage());
        }
    }
}
