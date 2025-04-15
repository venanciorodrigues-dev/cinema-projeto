package Pacote;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;



public class ConnPostCadastro {



    private Connection connection;



 

    public ConnPostCadastro() throws SQLException {

        this.connection = CasdastrarUser.Conection();

    }



 

    public Connection getConnection() {

        return connection;

    }



   

    public void setConnection(Connection connection) {

        this.connection = connection;

    }



 

    public boolean registerCliente(String nome, String email, String senha) {

        String sql = "INSERT INTO TUsuario (nome, email, senha) VALUES (?, ?, ?)";



        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

         

            stmt.setString(1, nome);

            stmt.setString(2, email);

            stmt.setString(3, senha);

          



         

            int rowsAffected = stmt.executeUpdate();



            return rowsAffected > 0;

        } catch (SQLException e) {

            if ("23505".equals(e.getSQLState())) {

                System.out.println("Erro: Já existe uma conta com esse nome ou e-mail!");

            } else {

                System.out.println("Erro ao registrar usuário: " + e.getMessage());

            }

            return false;

        }

    }}