package controller;

import model.Direccion;
import model.Juego;
import view.PanelTablero;
import view.VentanaPrincipal;

public class JuegoController {

    private Juego juego;
    private VentanaPrincipal ventana;

    public JuegoController(VentanaPrincipal ventana) {

        this.ventana = ventana;
        this.juego = new Juego();

        actualizarVista();
    }

    public void mover(Direccion direccion) {

        juego.mover(direccion);

        actualizarVista();
    }

    public void nuevaPartida() {

        juego.iniciarNuevaPartida();

        actualizarVista();
    }
    
    public int obtenerPuntaje() {
        return juego.getPuntaje();
    }

    private void actualizarVista() {

        PanelTablero tableroView = ventana.getPanelTablero();

        for (int fila = 0; fila < 4; fila++) {

            for (int columna = 0; columna < 4; columna++) {

                if (juego.getTablero().getFicha(fila, columna) == null) {

                    tableroView.vaciarCelda(fila, columna);

                } else {

                    int valor = juego.getTablero().getFicha(fila, columna).getValor();
                    
                    tableroView.mostrarFicha(fila, columna, valor);
                }
            }
        }

        ventana.actualizarPuntaje(juego.getPuntaje());

        if (juego.estaTerminado()) {
            ventana.mostrarGameOver();
        }
    }
}