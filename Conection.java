package Pacote;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conection {
    private static final String URL = "jdbc:postgresql://localhost:5432/CinemaDatabase";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = connect()) {
            if (connection != null) {
                JOptionPane.showMessageDialog(null, "Conexão estabelecida");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar: " + e.getMessage());
        }
    }
}