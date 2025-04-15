package Pacote;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JMenuItem;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnfilmes = new JMenu("Visualizar Filmes");
		mnfilmes.setForeground(Color.BLACK);
		
		mnfilmes.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnfilmes);
		
		JMenuItem MenuItemMinecreft = new JMenuItem("Um filme Minecreft");
		MenuItemMinecreft.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnfilmes.add(MenuItemMinecreft);
		
		JMenuItem MenuItemBen10 = new JMenuItem("Ben 10");
		MenuItemBen10.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnfilmes.add(MenuItemBen10);
		
		JMenuItem MenuItemSonic = new JMenuItem("Socic 3");
		MenuItemSonic.setFont(new Font("Segoe UI", Font.BOLD, 13));
		MenuItemSonic.setForeground(new Color(0, 0, 0));
		mnfilmes.add(MenuItemSonic);

		VisualizarFilmes VisualizarFilmes = new VisualizarFilmes();
		VisualizarFilmes.setVisible(true);
		dispose();
		
		JMenu mnNewMenu = new JMenu("Escolher Sessão");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				EscolherSessao frame = new EscolherSessao();
				frame .setVisible(true);
				dispose();
			}
		});
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Dia 01/03/25 Horario: 13;33");
		mnNewMenu.add(mntmNewMenuItem);
	
		
		JMenu mnsessao = new JMenu("Meus Ingressos");
		mnsessao.setForeground(Color.BLACK);
		mnsessao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MeusIngressos frame = new MeusIngressos();
				frame .setVisible(true);
				dispose();
				
			}
		});
		mnsessao.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnsessao);
		getContentPane().setLayout(null);
	}
}
