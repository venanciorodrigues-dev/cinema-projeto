package Pacote;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CasdastrarUser extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNome;
    private JTextField textEmail;
    private JPasswordField textSenha;

    public CasdastrarUser() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLUE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Cadastrar Usuário");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblTitulo.setBounds(99, 38, 262, 25);
        contentPane.add(lblTitulo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNome.setBounds(99, 74, 46, 14);
        contentPane.add(lblNome);

        textNome = new JTextField();
        textNome.setBounds(99, 88, 240, 20);
        contentPane.add(textNome);
        textNome.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmail.setBounds(99, 119, 46, 14);
        contentPane.add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(99, 133, 240, 20);
        contentPane.add(textEmail);
        textEmail.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSenha.setBounds(99, 164, 46, 14);
        contentPane.add(lblSenha);

        textSenha = new JPasswordField();
        textSenha.setBounds(99, 178, 240, 20);
        contentPane.add(textSenha);

        JButton btnProsseguir = new JButton("Prosseguir");
        btnProsseguir.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnProsseguir.setBounds(168, 225, 110, 25);
        contentPane.add(btnProsseguir);

        btnProsseguir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String nome = textNome.getText().trim();
        String email = textEmail.getText().trim();
        String senha = new String(textSenha.getPassword()).trim();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um e-mail válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = Conection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO TUsuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                textNome.setText("");
                textEmail.setText("");
                textSenha.setText("");

                Login login = new Login();
                login.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao inserir no banco: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean verificarUsuario(String nome, String email, String senha, Connection conn) {
        String sql = "SELECT * FROM TUsuarios WHERE nome = ? AND email = ? AND senha = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CasdastrarUser frame = new CasdastrarUser();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static Connection Conection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/CinemaDatabase"; // Corrigido para PostgreSQL
            String user = "postgres";
            String password = "root";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
