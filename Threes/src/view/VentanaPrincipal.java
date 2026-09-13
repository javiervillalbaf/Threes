package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.JuegoController;
import model.Direccion;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JLabel lblPuntaje;
    private PanelTablero panelTablero;

    private JuegoController controller;

    public VentanaPrincipal() {

        setTitle("Threes!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 600, 700);
        setMinimumSize(new Dimension(500, 600));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

        contentPane.setLayout(new BorderLayout(10, 10));

        setContentPane(contentPane);

        crearPanelSuperior();

        panelTablero = new PanelTablero();

        contentPane.add(panelTablero, BorderLayout.CENTER);

        JLabel lblInstrucciones = new JLabel("Usá las flechas del teclado para mover las fichas");

        lblInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);

        lblInstrucciones.setFont(new Font("SansSerif", Font.PLAIN, 14));

        contentPane.add(lblInstrucciones, BorderLayout.SOUTH);

        configurarTeclado();

        controller = new JuegoController(this);
    }

    private void crearPanelSuperior() {

        JPanel panelSuperior = new JPanel();

        panelSuperior.setLayout(new GridBagLayout());

        contentPane.add(panelSuperior, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("THREES!");

        lblTitulo.setFont(new Font("SansSerif", Font.BOLD,32));

        GridBagConstraints gbcTitulo = new GridBagConstraints();

        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.weightx = 1.0;
        gbcTitulo.anchor = GridBagConstraints.WEST;

        gbcTitulo.insets = new Insets(0, 0, 5, 0);

        panelSuperior.add(lblTitulo, gbcTitulo);

        lblPuntaje = new JLabel("Puntaje: 0");

        lblPuntaje.setFont(new Font("SansSerif", Font.BOLD, 18));

        GridBagConstraints gbcPuntaje = new GridBagConstraints();

        gbcPuntaje.gridx = 1;
        gbcPuntaje.gridy = 0;

        gbcPuntaje.anchor = GridBagConstraints.EAST;

        gbcPuntaje.insets = new Insets(0, 10, 5, 0);

        panelSuperior.add(lblPuntaje, gbcPuntaje);

        JButton btnPuntuaciones = new JButton("Ver puntuaciones");

        btnPuntuaciones.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btnPuntuaciones.addActionListener(e -> {
            mostrarPuntuaciones();
            requestFocusInWindow();
        });

        GridBagConstraints gbcPuntuaciones = new GridBagConstraints();

        gbcPuntuaciones.gridx = 0;
        gbcPuntuaciones.gridy = 1;

        gbcPuntuaciones.anchor = GridBagConstraints.WEST;

        gbcPuntuaciones.insets = new Insets(5, 0, 0, 0);

        panelSuperior.add(btnPuntuaciones, gbcPuntuaciones);

        JButton btnNuevaPartida = new JButton("Nueva partida");

        btnNuevaPartida.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btnNuevaPartida.addActionListener(e -> {
            controller.nuevaPartida();
            requestFocusInWindow();
        });

        GridBagConstraints gbcNuevaPartida = new GridBagConstraints();

        gbcNuevaPartida.gridx = 1;
        gbcNuevaPartida.gridy = 1;

        gbcNuevaPartida.anchor = GridBagConstraints.EAST;

        gbcNuevaPartida.insets = new Insets(5, 10, 0, 0);

        panelSuperior.add(btnNuevaPartida,gbcNuevaPartida);
    }

    private void configurarTeclado() {

        setFocusable(true);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                int tecla = e.getKeyCode();

                if (tecla == KeyEvent.VK_UP) {
                    controller.mover(Direccion.ARRIBA);
                } else if (tecla == KeyEvent.VK_DOWN) {
                    controller.mover(Direccion.ABAJO);
                } else if (tecla == KeyEvent.VK_LEFT) {
                    controller.mover(Direccion.IZQUIERDA);
                } else if (tecla == KeyEvent.VK_RIGHT) {
                    controller.mover(Direccion.DERECHA);
                }
            }
        });
    }

    public PanelTablero getPanelTablero() {
        return panelTablero;
    }

    public void actualizarPuntaje(int puntaje) {

        lblPuntaje.setText("Puntaje: " + puntaje);
    }

    public void mostrarGameOver() {

        String nombre = JOptionPane.showInputDialog(
                this,
                "¡Game Over!\nPuntaje final: " + controller.obtenerPuntaje() + "\n\nIngresá tu nombre para guardar tu puntuación:",
                "Fin del juego",
                JOptionPane.PLAIN_MESSAGE
        );

        if (nombre != null && !nombre.isBlank()) {
            controller.guardarPuntuacion(nombre);
        }
    }

    public void mostrarPuntuaciones() {

        VentanaPuntuaciones ventanaPuntuaciones = new VentanaPuntuaciones(this, controller.obtenerPuntuaciones());

        ventanaPuntuaciones.setVisible(true);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            VentanaPrincipal ventana = new VentanaPrincipal();

            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            ventana.requestFocusInWindow();
        });
    }
}