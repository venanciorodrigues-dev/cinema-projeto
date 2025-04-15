package Pacote;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;

public class VisualizarFilmes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarFilmes frame = new VisualizarFilmes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VisualizarFilmes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Um Filme Minecraft");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 0, 153, 14);
		contentPane.add(lblNewLabel);
		
		JTextArea BiodoFilmeMine = new JTextArea();
		BiodoFilmeMine.setFont(new Font("Monospaced", Font.BOLD, 13));
		BiodoFilmeMine.setText("Uma aventura divertida para toda a família.");
		BiodoFilmeMine.setLineWrap(true);
		BiodoFilmeMine.setWrapStyleWord(true);
		BiodoFilmeMine.setBounds(10, 22, 414, 23);
		contentPane.add(BiodoFilmeMine);
		
		JButton btnNewButton = new JButton("Solicitar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(10, 56, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Sonic 3");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 90, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextArea txtrSonicKnucklesE = new JTextArea();
		txtrSonicKnucklesE.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtrSonicKnucklesE.setText("Sonic, Knuckles e Tails estão de volta para uma aventura épica");
		txtrSonicKnucklesE.setBounds(10, 110, 414, 23);
		contentPane.add(txtrSonicKnucklesE);
		
		JLabel lblNewLabel_2 = new JLabel("Ben 10");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 172, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JTextArea txtrBenDeveEnfrentar = new JTextArea();
		txtrBenDeveEnfrentar.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtrBenDeveEnfrentar.setText("Não perca a estreia do Ben 10 contra o Universo");
		txtrBenDeveEnfrentar.setBounds(10, 197, 414, 22);
		contentPane.add(txtrBenDeveEnfrentar);
		
		JButton btnNewButton_1 = new JButton("Solicitar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(10, 144, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Solicitar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(10, 227, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
