package utils;

public class Graficos {

    public static void exibirBarra(String descricao, double valor) {
        int asteriscos = (int) Math.round(valor / 5.0);  // Escala de 1 "*" por 5 unidades
        StringBuilder barra = new StringBuilder();
        for (int i = 0; i < asteriscos; i++) {
            barra.append("*");
        }

        System.out.printf("%-20s | %s (%.1f)%n", descricao, barra.toString(), valor);
    }
}
