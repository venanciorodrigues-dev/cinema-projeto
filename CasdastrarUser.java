package Pacote;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class CasdastrarUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private static String nomeUsuario;
	private static String emailUsuario;
	private static String senhaUsuario;
	private final JButton btnProsseguir = new JButton("Prosseguir");
	private JPasswordField textSenha;


	public CasdastrarUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel CasdastrarUsuário = new JLabel("Cadastrar Usuário");
		CasdastrarUsuário.setForeground(Color.WHITE);
		CasdastrarUsuário.setBackground(Color.WHITE);
		CasdastrarUsuário.setFont(new Font("Tahoma", Font.BOLD, 25));
		CasdastrarUsuário.setBounds(99, 38, 262, 25);
		contentPane.add(CasdastrarUsuário);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(99, 74, 46, 14);
		contentPane.add(lblNewLabel);
		
		textNome = new JTextField();
		textNome.setBounds(99, 88, 240, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(99, 119, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textEmail = new JTextField();
		textEmail.setBounds(99, 133, 240, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(99, 164, 46, 14);
		contentPane.add(lblNewLabel_2);
		btnProsseguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeUsuario = textNome.getText();
                emailUsuario = textEmail.getText();
				senhaUsuario = textSenha.getText();
			    
                registrarUsuario();
				Login login = new Login();
				login.setVisible(true);
				dispose();
				
				JOptionPane.showMessageDialog(null, "Cadatrado com sucesso");
				
			}
		});
		btnProsseguir.setBounds(168, 225, 110, 25);
		contentPane.add(btnProsseguir);
		btnProsseguir.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textSenha = new JPasswordField();
		textSenha.setBounds(99, 178, 240, 20);
		contentPane.add(textSenha);
	}
	
	private void registrarUsuario() {
	    String nome = textNome.getText().trim();
	    String email = textEmail.getText().trim();
	    String senha = new String(textSenha.getPassword()).trim(); // Obtém a senha corretamente

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

	    String sql = "INSERT INTO tusuarios (nome, email, senha) VALUES (?, ?, ?)";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, nome);
	        stmt.setString(2, email);
	        stmt.setString(3, senha);
	        
	        int rowsInserted = stmt.executeUpdate(); // Executa o INSERT

	        if (rowsInserted > 0) {
	            conn.commit(); // Salva a transação no banco (necessário em alguns bancos)
	            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
	            textNome.setText("");
	            textEmail.setText("");
	            textSenha.setText("");
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

	        String sql = "SELECT * FROM tusuarios WHERE nome = ? AND email = ? AND senha = ?"; 

	        

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {



	            stmt.setString(1, nome);

	            stmt.setString(2, email);
	            
	            stmt.setString(3, senha);

	            

	            ResultSet rs = stmt.executeQuery();



	            return rs.next(); 

	            

	        } catch (SQLException e) {

	            e.printStackTrace();

	        }

	        return false;

	    }
	    
	    

	

		public static void main(String[] args) {
			try {
				CasdastrarUser cad = new CasdastrarUser();
				cad.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			cad.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}



	        new CasdastrarUser();



	    }

        public static Connection Conection() {
            try {
                String url = "jdbc:mysql://localhost:3306/seu_banco";
                String user = "seu_usuario";
                String password = "sua_senha";
                
                Connection conn = DriverManager.getConnection(url, user, password);
                return conn;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

		}