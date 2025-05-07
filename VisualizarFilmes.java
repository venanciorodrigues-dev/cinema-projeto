package Pacote;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class VisualizarFilmes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VisualizarFilmes frame = new VisualizarFilmes();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VisualizarFilmes() {
        setTitle("Filmes Disponíveis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(30, 30, 60));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Filmes em Cartaz");
        titulo.setFont(new Font("Verdana", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titulo, BorderLayout.NORTH);

        JPanel filmesPanel = new JPanel();
        filmesPanel.setOpaque(false);
        filmesPanel.setLayout(new GridLayout(0, 1, 15, 15)); // lista vertical

        filmesPanel.add(criarCardFilme("Um Filme Minecraft", "Uma aventura divertida para toda a família."));
        filmesPanel.add(criarCardFilme("Sonic 3", "Sonic, Knuckles e Tails estão de volta para uma aventura épica."));
        filmesPanel.add(criarCardFilme("Ben 10", "Não perca a estreia do Ben 10 contra o Universo."));

        JScrollPane scroll = new JScrollPane(filmesPanel);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        contentPane.add(scroll, BorderLayout.CENTER);
    }

    private JPanel criarCardFilme(String titulo, String descricao) {
        JPanel card = new JPanel();
        card.setBackground(new Color(70, 130, 180));
        card.setBorder(new LineBorder(Color.WHITE, 2, true));
        card.setLayout(new BorderLayout(10, 10));
        card.setPreferredSize(new Dimension(550, 100));
        card.setMaximumSize(new Dimension(550, 100));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(new EmptyBorder(5, 10, 0, 10));

        JTextArea areaDescricao = new JTextArea(descricao);
        areaDescricao.setWrapStyleWord(true);
        areaDescricao.setLineWrap(true);
        areaDescricao.setEditable(false);
        areaDescricao.setFont(new Font("Arial", Font.PLAIN, 13));
        areaDescricao.setForeground(Color.WHITE);
        areaDescricao.setBackground(new Color(70, 130, 180));
        areaDescricao.setBorder(new EmptyBorder(0, 10, 0, 10));

        JButton btnSolicitar = new JButton("Solicitar");
        btnSolicitar.setFocusPainted(false);
        btnSolicitar.setFont(new Font("Arial", Font.BOLD, 12));
        btnSolicitar.setBackground(new Color(245, 245, 245));
        btnSolicitar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnSolicitar.setForeground(Color.BLACK);
        btnSolicitar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Solicitação enviada para: " + titulo);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(btnSolicitar);

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(areaDescricao, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }
}
