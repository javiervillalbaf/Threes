package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {

    public static final int TAMANIO = 4;

    private Ficha[][] fichas;
    private Random random;

    public Tablero() {
        fichas = new Ficha[TAMANIO][TAMANIO];
        random = new Random();
    }

    public void inicializar() {

        fichas = new Ficha[TAMANIO][TAMANIO];

        agregarFichaInicial();
        agregarFichaInicial();
        agregarFichaInicial();
    }

    private void agregarFichaInicial() {

        List<int[]> posicionesLibres = obtenerPosicionesLibres();

        if (posicionesLibres.isEmpty()) {
            return;
        }

        int indice = random.nextInt(posicionesLibres.size());
        int[] posicion = posicionesLibres.get(indice);

        fichas[posicion[0]][posicion[1]] = generarFicha();
    }

    private Ficha generarFicha() {

        int valor = random.nextInt(3) + 1;

        return new Ficha(valor);
    }

    public Ficha getFicha(int fila, int columna) {

        return fichas[fila][columna];
    }

    public boolean hayEspacioLibre() {

        for (int fila = 0; fila < TAMANIO; fila++) {
            for (int columna = 0; columna < TAMANIO; columna++) {

                if (fichas[fila][columna] == null) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<int[]> obtenerPosicionesLibres() {

        List<int[]> posiciones = new ArrayList<>();

        for (int fila = 0; fila < TAMANIO; fila++) {
            for (int columna = 0; columna < TAMANIO; columna++) {

                if (fichas[fila][columna] == null) {
                    posiciones.add(new int[]{fila, columna});
                }
            }
        }

        return posiciones;
    }

    public boolean mover(Direccion direccion) {

        boolean movimientoRealizado = false;

        for (int i = 0; i < TAMANIO; i++) {

            List<Ficha> linea = obtenerLinea(i, direccion);

            List<Ficha> resultado = procesarLinea(linea);

            if (!lineasIguales(linea, resultado)) {
                movimientoRealizado = true;
            }

            guardarLinea(i, direccion, resultado);
        }

        return movimientoRealizado;
    }

    private List<Ficha> obtenerLinea(int indice, Direccion direccion) {

        List<Ficha> linea = new ArrayList<>();

        if (direccion == Direccion.IZQUIERDA ||
            direccion == Direccion.DERECHA) {

            for (int columna = 0; columna < TAMANIO; columna++) {

                int columnaReal = direccion == Direccion.IZQUIERDA ? columna : TAMANIO - 1 - columna;

                linea.add(fichas[indice][columnaReal]);
            }

        } else {

            for (int fila = 0; fila < TAMANIO; fila++) {

                int filaReal = direccion == Direccion.ARRIBA ? fila : TAMANIO - 1 - fila;

                linea.add(fichas[filaReal][indice]);
            }
        }

        return linea;
    }

    private List<Ficha> procesarLinea(List<Ficha> linea) {

        List<Ficha> resultado = new ArrayList<>(linea);

        for (int i = 0; i < TAMANIO - 1 ; i++) {
        	
        	Ficha actual = resultado.get(i);
        	Ficha siguiente = resultado.get(i + 1);
        	
        	if (actual == null && siguiente != null) {

                resultado.set(i, siguiente);
                resultado.set(i + 1, null);
                
        	}
        	
        	if (actual != null && siguiente != null && actual.puedeCombinarCon(siguiente)) {

                Ficha combinada = actual.combinarCon(siguiente);

                resultado.set(i, combinada);
                resultado.set(i + 1, null);
                
               
        	}
        }
        	
        	return resultado;
        	
    }
        

    private void guardarLinea(
            int indice,
            Direccion direccion,
            List<Ficha> linea) {

        for (int i = 0; i < TAMANIO; i++) {

            if (direccion == Direccion.IZQUIERDA) {

                fichas[indice][i] = linea.get(i);

            } else if (direccion == Direccion.DERECHA) {

                fichas[indice][TAMANIO - 1 - i] = linea.get(i);

            } else if (direccion == Direccion.ARRIBA) {

                fichas[i][indice] = linea.get(i);

            } else {

                fichas[TAMANIO - 1 - i][indice] = linea.get(i);
            }
        }
    }

    private boolean lineasIguales(
            List<Ficha> original,
            List<Ficha> nueva) {

        for (int i = 0; i < TAMANIO; i++) {

            Ficha fichaOriginal = original.get(i);
            Ficha fichaNueva = nueva.get(i);

            if (fichaOriginal == null && fichaNueva == null) {
                continue;
            }

            if (fichaOriginal == null || fichaNueva == null) {
                return false;
            }

            if (fichaOriginal.getValor() != fichaNueva.getValor()) {
                return false;
            }
        }

        return true;
    }

    public void agregarFicha(Direccion direccion) {

        List<int[]> posiciones = obtenerPosicionesBorde(direccion);

        List<int[]> libres = new ArrayList<>();

        for (int[] posicion : posiciones) {

            if (fichas[posicion[0]][posicion[1]] == null) {
                libres.add(posicion);
            }
        }

        if (libres.isEmpty()) {
            return;
        }

        int indice = random.nextInt(libres.size());
        int[] posicion = libres.get(indice);

        fichas[posicion[0]][posicion[1]] = generarFicha();
    }

    private List<int[]> obtenerPosicionesBorde(Direccion direccion) {

        List<int[]> posiciones = new ArrayList<>();

        switch (direccion) {

            case IZQUIERDA:

                for (int fila = 0; fila < TAMANIO; fila++) {
                    posiciones.add(new int[]{fila, TAMANIO - 1});
                }

                break;

            case DERECHA:

                for (int fila = 0; fila < TAMANIO; fila++) {
                    posiciones.add(new int[]{fila, 0});
                }

                break;

            case ARRIBA:

                for (int columna = 0; columna < TAMANIO; columna++) {
                    posiciones.add(new int[]{TAMANIO - 1, columna});
                }

                break;

            case ABAJO:

                for (int columna = 0; columna < TAMANIO; columna++) {
                    posiciones.add(new int[]{0, columna});
                }

                break;
        }

        return posiciones;
    }

    public boolean hayMovimientosPosibles() {

        if (hayEspacioLibre()) {
            return true;
        }

        for (int fila = 0; fila < TAMANIO; fila++) {

            for (int columna = 0; columna < TAMANIO; columna++) {

                Ficha actual = fichas[fila][columna];

                if (columna + 1 < TAMANIO) {

                    Ficha derecha = fichas[fila][columna + 1];

                    if (actual.puedeCombinarCon(derecha)) {
                        return true;
                    }
                }

                if (fila + 1 < TAMANIO) {

                    Ficha abajo = fichas[fila + 1][columna];

                    if (actual.puedeCombinarCon(abajo)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}