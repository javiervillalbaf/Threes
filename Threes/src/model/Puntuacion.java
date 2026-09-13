package model;

public class Puntuacion implements Comparable<Puntuacion> {

    private String nombre;
    private int puntaje;

    public Puntuacion(String nombre, int puntaje) {

        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public int compareTo(Puntuacion otra) {
        return Integer.compare(otra.puntaje, this.puntaje);
    }

    @Override
    public String toString() {
        return nombre + " - " + puntaje;
    }
}