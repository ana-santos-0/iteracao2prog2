package utils;

public class Graficos {

    public static void Graficos(String titulo, double valor, int max) {
        System.out.print(titulo + ": ");
        int estrelas = (int) ((valor / max) * 50); // escala de 0 a 50 estrelas
        for (int i = 0; i < estrelas; i++) {
            System.out.print("*");
        }
        System.out.println(" (" + String.format("%.1f", valor) + ")");
    }
}