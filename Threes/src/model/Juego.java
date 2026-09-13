package model;

public class Juego {

    private Tablero tablero;
    private int puntaje;
    private boolean terminado;

    public Juego() {
        tablero = new Tablero();
        iniciarNuevaPartida();
    }

    public void iniciarNuevaPartida() {

        tablero.inicializar();
        puntaje = 0;
        terminado = false;
    }

    public void mover(Direccion direccion) {

        if (terminado) {
            return;
        }

        boolean movimientoRealizado = tablero.mover(direccion);

        if (!movimientoRealizado) {
            return;
        }

        tablero.agregarFicha(direccion);

        actualizarPuntaje();

        if (!tablero.hayMovimientosPosibles()) {
            terminado = true;
        }
    }

    private void actualizarPuntaje() {

        int nuevoPuntaje = 0;

        for (int fila = 0; fila < Tablero.TAMANIO; fila++) {

            for (int columna = 0; columna < Tablero.TAMANIO; columna++) {

                Ficha ficha = tablero.getFicha(fila, columna);

                if (ficha != null) {
                    nuevoPuntaje += ficha.obtenerPuntaje();
                }
            }
        }

        puntaje = nuevoPuntaje;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean estaTerminado() {
        return terminado;
    }
}
