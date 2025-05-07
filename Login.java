package Pacote;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Login() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLUE);
		contentPanel.setForeground(Color.BLUE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel texLOGIN = new JLabel("LOGIN");
		texLOGIN.setForeground(Color.WHITE);
		texLOGIN.setFont(new Font("Tahoma", Font.BOLD, 23));
		texLOGIN.setBounds(166, 35, 86, 34);
		contentPanel.add(texLOGIN);

		textField = new JTextField();
		textField.setBounds(100, 104, 239, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(100, 161, 239, 20);
		contentPanel.add(passwordField);

		JLabel textSENHA = new JLabel("SENHA:");
		textSENHA.setForeground(Color.WHITE);
		textSENHA.setFont(new Font("Tahoma", Font.BOLD, 12));
		textSENHA.setBounds(99, 148, 86, 14);
		contentPanel.add(textSENHA);

		JLabel lblNewLabel = new JLabel("NOME:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(100, 92, 46, 14);
		contentPanel.add(lblNewLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textField.getText().trim();
				String senha = new String(passwordField.getPassword()).trim();

				try {
					Connection conn = Conection.conectar();
					if (conn == null) {
						JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.");
						return;
					}

					String sql = "SELECT * FROM TUsuarios WHERE nome = ? AND senha = ?";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, nome);
					stmt.setString(2, senha);

					ResultSet rs = stmt.executeQuery();

					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
						Menu menu = new Menu();
						menu.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Nome ou senha incorretos.");
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro de banco de dados: " + ex.getMessage());
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}
