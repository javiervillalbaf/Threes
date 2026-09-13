package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaPuntuaciones {

    private static final int MAXIMO_PUNTUACIONES = 10;

    private List<Puntuacion> puntuaciones;

    public ListaPuntuaciones() {
        puntuaciones = new ArrayList<>();
    }

    public void agregar(Puntuacion puntuacion) {

        puntuaciones.add(puntuacion);
        Collections.sort(puntuaciones);

        while (puntuaciones.size() > MAXIMO_PUNTUACIONES) {
            puntuaciones.remove(puntuaciones.size() - 1);
        }
    }

    public List<Puntuacion> obtenerTodas() {
        return new ArrayList<>(puntuaciones);
    }
}