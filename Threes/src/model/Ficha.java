package model;

public class Ficha {

    private int valor;

    public Ficha(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public boolean puedeCombinarCon(Ficha otra) {

        if (otra == null) {
            return false;
        }

        // 1 + 2 o 2 + 1
        if ((valor == 1 && otra.valor == 2) ||
            (valor == 2 && otra.valor == 1)) {
            return true;
        }

        // A partir de 3, solamente se combinan fichas iguales
        return valor >= 3 && valor == otra.valor;
    }

    public Ficha combinarCon(Ficha otra) {

        if (!puedeCombinarCon(otra)) {
            return null;
        }

        if ((valor == 1 && otra.valor == 2) ||
            (valor == 2 && otra.valor == 1)) {
            return new Ficha(3);
        }

        return new Ficha(valor * 2);
    }

    public int obtenerPuntaje() {

        if (valor < 3) {
            return 0;
        }

        return valor;
    }
}