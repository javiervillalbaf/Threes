package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
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

        JButton btnNuevaPartida = new JButton("Nueva partida");

        btnNuevaPartida.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btnNuevaPartida.addActionListener(e -> controller.nuevaPartida());

        GridBagConstraints gbcNuevaPartida = new GridBagConstraints();

        gbcNuevaPartida.gridx = 1;
        gbcNuevaPartida.gridy = 1;

        gbcNuevaPartida.anchor = GridBagConstraints.EAST;

        gbcNuevaPartida.insets = new Insets(5, 10, 0, 0);

        panelSuperior.add(btnNuevaPartida,gbcNuevaPartida);
    }

    private void configurarTeclado() {

        JRootPane rootPane = getRootPane();

        rootPane.getInputMap(JRootPane.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moverArriba");

        rootPane.getInputMap(JRootPane.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moverAbajo");

        rootPane.getInputMap(JRootPane.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moverIzquierda");

        rootPane.getInputMap(JRootPane.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moverDerecha");

        rootPane.getActionMap().put(
                "moverArriba",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.mover(Direccion.ARRIBA);
                    }
                }
        );

        rootPane.getActionMap().put(
                "moverAbajo",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.mover(Direccion.ABAJO);
                    }
                }
        );

        rootPane.getActionMap().put(
                "moverIzquierda",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.mover(Direccion.IZQUIERDA);
                    }
                }
        );

        rootPane.getActionMap().put(
                "moverDerecha",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.mover(Direccion.DERECHA);
                    }
                }
        );
    }

    public PanelTablero getPanelTablero() {
        return panelTablero;
    }

    public void actualizarPuntaje(int puntaje) {

        lblPuntaje.setText("Puntaje: " + puntaje);
    }

    public void mostrarGameOver() {

        javax.swing.JOptionPane.showMessageDialog(this, "¡Game Over!\nPuntaje: " + controller.obtenerPuntaje(), "Fin del juego", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            VentanaPrincipal ventana = new VentanaPrincipal();

            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}