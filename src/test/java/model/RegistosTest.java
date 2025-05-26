package model;

import org.junit.jupiter.api.Test;
import utils.Registos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistosTest {

    @Test
    void testCalcularMedia() {
        List<Registo> registos = new ArrayList<>();
        registos.add(new Registo(80, 36.5, 98, new Date()));
        registos.add(new Registo(90, 37.0, 97, new Date()));

        double media = Registos.calcularMedia("frequencia", registos, new Date(0), new Date());
        assertEquals(85.0, media, 0.01);
    }

    @Test
    void testCalcularDesvioPadrao() {
        List<Registo> registos = new ArrayList<>();
        registos.add(new Registo(80, 36.5, 98, new Date()));
        registos.add(new Registo(90, 37.0, 97, new Date()));

        double desvioPadrao = Registos.calcularDesvioPadrao("frequencia", registos, new Date(0), new Date());
        assertEquals(5.0, desvioPadrao, 0.01);
    }

    @Test
    void testCalcularMinimo() {
        List<Registo> registos = new ArrayList<>();
        registos.add(new Registo(80, 36.5, 98, new Date()));
        registos.add(new Registo(90, 37.0, 97, new Date()));

        double minimo = Registos.calcularMinimo("frequencia", registos, new Date(0), new Date());
        assertEquals(80.0, minimo, 0.01);
    }

    @Test
    void testCalcularMaximo() {
        List<Registo> registos = new ArrayList<>();
        registos.add(new Registo(80, 36.5, 98, new Date()));
        registos.add(new Registo(90, 37.0, 97, new Date()));

        double maximo = Registos.calcularMaximo("frequencia", registos, new Date(0), new Date());
        assertEquals(90.0, maximo, 0.01);
    }
}
