package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTablero extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int TAMANIO_TABLERO = 4;

    private JPanel[][] celdas;

    private Map<Integer, Color> colores;

    public PanelTablero() {

        setLayout(new GridLayout(TAMANIO_TABLERO, TAMANIO_TABLERO, 8, 8));

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inicializarColores();

        celdas = new JPanel[TAMANIO_TABLERO][TAMANIO_TABLERO];

        inicializarTablero();
    }

    private void inicializarColores() {

        colores = new HashMap<>();

        colores.put(1, new Color(240, 220, 170));
        colores.put(2, new Color(220, 190, 150));
        colores.put(3, new Color(220, 220, 220));
        colores.put(6, new Color(200, 200, 200));
        colores.put(12, new Color(180, 210, 230));
        colores.put(24, new Color(160, 190, 220));
        colores.put(48, new Color(140, 170, 210));
        colores.put(96, new Color(120, 150, 200));
        colores.put(192, new Color(100, 130, 190));
    }

    private void inicializarTablero() {

        for (int fila = 0; fila < TAMANIO_TABLERO; fila++) {

            for (int columna = 0; columna < TAMANIO_TABLERO; columna++) {

                JPanel celda = crearCelda();

                celdas[fila][columna] = celda;

                add(celda);
            }
        }
    }

    private JPanel crearCelda() {

        JPanel celda = new JPanel();

        celda.setLayout(new GridLayout(1, 1));

        celda.setBackground(new Color(210, 210, 210));

        celda.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        return celda;
    }

    public void mostrarFicha(int fila, int columna, int valor) {

        JPanel celda = celdas[fila][columna];

        celda.removeAll();

        JLabel ficha = crearFicha(valor);

        celda.add(ficha);

        celda.revalidate();
        celda.repaint();
    }

    public void vaciarCelda(int fila, int columna) {

        JPanel celda = celdas[fila][columna];

        celda.removeAll();

        celda.setBackground(new Color(210, 210, 210));

        celda.revalidate();
        celda.repaint();
    }

    private JLabel crearFicha(int valor) {

        JLabel ficha = new JLabel(String.valueOf(valor));

        ficha.setHorizontalAlignment(SwingConstants.CENTER);

        ficha.setVerticalAlignment(SwingConstants.CENTER);

        ficha.setFont(obtenerFuente(valor));

        ficha.setOpaque(true);

        ficha.setBackground(obtenerColor(valor));

        ficha.setForeground(Color.BLACK);

        ficha.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 1));

        return ficha;
    }

    private Font obtenerFuente(int valor) {

        int tamanio = 32;

        if (valor >= 100) {
            tamanio = 26;
        }

        if (valor >= 1000) {
            tamanio = 22;
        }

        return new Font("SansSerif", Font.BOLD, tamanio);
    }

    private Color obtenerColor(int valor) {

        if (colores.containsKey(valor)) {
            return colores.get(valor);
        }

        return new Color(90, 120, 180);
    }
}