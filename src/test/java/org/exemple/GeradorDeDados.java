package org.exemple;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
public class GeradorDeDados {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static String gerarNome() {
        return faker.name().fullName();
    }

    public static String gerarNacionalidade() {
        return faker.country().name();
    }

    public static String gerarEquipe() {
        String[] equipesF1 = {
                "Mercedes", "Ferrari", "Red Bull",
                "McLaren", "Aston Martin", "Alpine", "Williams"
        };
        return equipesF1[ThreadLocalRandom.current().nextInt(equipesF1.length)];
    }

    public static String gerarTitulos() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(0, 20)); // de 0 a 7 títulos
    }

}
