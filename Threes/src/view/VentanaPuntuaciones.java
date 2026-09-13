package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Puntuacion;

public class VentanaPuntuaciones extends JDialog {

    private static final long serialVersionUID = 1L;

    public VentanaPuntuaciones(JFrame padre, List<Puntuacion> puntuaciones) {

        super(padre, "Mejores puntuaciones", true);

        setSize(380, 420);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Mejores puntuaciones", SwingConstants.CENTER);

        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(lblTitulo, BorderLayout.NORTH);

        add(new JScrollPane(crearTabla(puntuaciones)), BorderLayout.CENTER);
        add(crearPanelInferior(), BorderLayout.SOUTH);
    }

    private JTable crearTabla(List<Puntuacion> puntuaciones) {

        String[] columnas = {"#", "Nombre", "Puntaje"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        int posicion = 1;

        for (Puntuacion puntuacion : puntuaciones) {

            modelo.addRow(new Object[]{posicion, puntuacion.getNombre(), puntuacion.getPuntaje()});

            posicion++;
        }

        JTable tabla = new JTable(modelo);

        tabla.setRowHeight(26);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        return tabla;
    }

    private JPanel crearPanelInferior() {

        JPanel panel = new JPanel();

        JButton btnCerrar = new JButton("Cerrar");

        btnCerrar.addActionListener(e -> dispose());

        panel.add(btnCerrar);

        return panel;
    }
}