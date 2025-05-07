package Pacote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnPostCadastro {

    private Connection connection;

    public ConnPostCadastro() throws SQLException {
        this.connection = Conection.conectar(); // Usa a classe de conexão corrigida
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean registerCliente(String nome, String email, String senha) {
        // Verifica se usuário ou e-mail já existem
        if (usuarioExiste(nome, email)) {
            System.out.println("⚠️ Já existe um usuário com esse nome ou e-mail.");
            return false;
        }

        String sql = "INSERT INTO TUsuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("❌ Erro ao registrar usuário: " + e.getMessage());
            return false;
        }
    }

    // Verifica se já existe um usuário com mesmo nome ou e-mail
    private boolean usuarioExiste(String nome, String email) {
        String sql = "SELECT 1 FROM TUsuarios WHERE nome = ? OR email = ? LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // retorna true se encontrou algo

        } catch (SQLException e) {
            System.out.println("❌ Erro ao verificar duplicidade: " + e.getMessage());
            return true; // por segurança, assume que existe se falhar
        }
    }
}
