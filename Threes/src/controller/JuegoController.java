package controller;

import java.util.List;

import model.Direccion;
import model.Juego;
import model.ListaPuntuaciones;
import model.Puntuacion;
import view.PanelTablero;
import view.VentanaPrincipal;

public class JuegoController {

    private Juego juego;
    private VentanaPrincipal ventana;
    private ListaPuntuaciones listaPuntuaciones;

    public JuegoController(VentanaPrincipal ventana) {

        this.ventana = ventana;
        this.juego = new Juego();
        this.listaPuntuaciones = new ListaPuntuaciones();

        actualizarVista();
    }

    public void mover(Direccion direccion) {

        if (juego.estaTerminado()) {
            return;
        }

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

    public void guardarPuntuacion(String nombre) {

        String nombreLimpio = nombre.trim();

        if (nombreLimpio.isEmpty()) {
            return;
        }

        listaPuntuaciones.agregar(new Puntuacion(nombreLimpio, juego.getPuntaje()));
    }

    public List<Puntuacion> obtenerPuntuaciones() {
        return listaPuntuaciones.obtenerTodas();
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