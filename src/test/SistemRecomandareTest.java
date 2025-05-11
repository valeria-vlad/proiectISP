package test;


import model.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.sound.sampled.Port;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class SistemRecomandareTest {
    SistemRecomandare sistemRecomandare;

    @Test
    public void TestareRecomandareMaster() {
        sistemRecomandare = new SistemRecomandare();
        Student student = new Student("Andrei Marian", "and@example.com", "1234567890", "01-01-2000", "2023", 7.5);
        Student student1 = new Student("Alice Brown", "alice@example.com", "1234561111", "02-02-1999", "2022", 9.0);
        Student student2 = new Student("Bob White", "bob@example.com", "1234562222", "03-03-1998", "2021", 7.0);
        Student student3 = new Student("Charlie Green", "charlie@example.com", "1234563333", "04-04-1997", "2020", 8.2);
        Student student4 = new Student("Diana Blue", "diana@example.com", "1234564444", "05-05-1996", "2019", 6.8);
        Student student5 = new Student("Eve Black", "eve@example.com", "1234565555", "06-06-1995", "2018", 8.7);
        Master masterAI = new Master(NumeMaster.Inteligenta_Artificiala,
                "Artificial Intelligence", EnumSet.of(Tehnologii.Python),
                EnumSet.noneOf(CompetenteSpecifice.class), 30);
        List<Master> programe_disponible = new ArrayList<>();
        programe_disponible.add(masterAI);
        Map<NumeMaster, CerinteMinime> cerinte = new HashMap<>();
        cerinte.put(masterAI.getNume(), new CerinteMinime(8.0));
        List<Master> output = sistemRecomandare.recomandaProgrameMaster(student, programe_disponible, cerinte);
        assertEquals(output.size(), 1);

        output = sistemRecomandare.recomandaProgrameMaster(student1, programe_disponible, cerinte);
        assertEquals(1, output.size()); // Nota mare, fara tehnologii
        student2.addTechnology(Tehnologii.Python);
        output = sistemRecomandare.recomandaProgrameMaster(student2, programe_disponible, cerinte);
        assertEquals(1, output.size()); // Nota mica, cu tehnologii
        student3.addTechnology(Tehnologii.Python);
        output = sistemRecomandare.recomandaProgrameMaster(student3, programe_disponible, cerinte);
        assertEquals(1, output.size()); // Nota medie, cu tehnologii
        student4.addTechnology(Tehnologii.Java);
        output = sistemRecomandare.recomandaProgrameMaster(student4, programe_disponible, cerinte);
        assertEquals(1, output.size()); // Nota foarte mica
        student5.addTechnology(Tehnologii.Python);
        output =  sistemRecomandare.recomandaProgrameMaster(student5, programe_disponible, cerinte);
        assertEquals(1, output.size()); // Nota mare, cu tehnologii
    }

    @Test
    public void TestCalculeazaScorCompatibilitate() {
        sistemRecomandare = new SistemRecomandare();
        Student student = new Student("Andrei Marian", "and@example.com", "1234567890", "01-01-2000", "2023", 7.5);
        Student student2 = new Student("Maria Popescu", "maria@example.com", "0786987234", "05-07-2003", "2022", 8.0);
        Student student3 = new Student("Charlie Green", "charlie@example.com", "1234563333", "04-04-1997", "2020", 9.8);
        Master masterAI = new Master(NumeMaster.Inteligenta_Artificiala,
                "Artificial Intelligence", EnumSet.of(Tehnologii.Python), EnumSet.noneOf(CompetenteSpecifice.class), 30);

        // in urmatoarele situatii studentul nu a ales acest master ca fiind prima optiune:
        CerinteMinime cerinte_doar_medie = new CerinteMinime(8.0);

        CerinteMinime cerinte_medie_tehnologie = new CerinteMinime(8.0);
        cerinte_medie_tehnologie.adaugaTehnologieRecomandata(Tehnologii.Python);

        System.out.println("Nu exista cerinte explicite pentru acest master");
        double score = sistemRecomandare.calculeazaScorCompatibilitate(student, masterAI, null);
        assertEquals(50.0, score, 0.01);

        System.out.println("Studentul are media mai mica decat cea minima");
        student.addTechnology(Tehnologii.Python);
        score = sistemRecomandare.calculeazaScorCompatibilitate(student, masterAI, cerinte_doar_medie);
        assertEquals(0.0, score, 0.01);

        System.out.println("Studentul are exact media minima si niciuna din tehnologiile necesare");
        score = sistemRecomandare.calculeazaScorCompatibilitate(student2, masterAI, cerinte_doar_medie);
        assertEquals(40.0, score, 0.01);

        System.out.println("Studentul are exact media minima si toate tehnologiile necesare");
        student2.addTechnology(Tehnologii.Python);
        score = sistemRecomandare.calculeazaScorCompatibilitate(student2, masterAI, cerinte_medie_tehnologie);
        assertEquals(65.0, score, 0.01);

        System.out.println("Studentul are medie foarte mare dar nu are niciuna din cerintele minime");
        score = sistemRecomandare.calculeazaScorCompatibilitate(student3, masterAI, cerinte_medie_tehnologie);
        assertEquals(58.0, score, 0.01);

        System.out.println("Studentul are medie foarte mare si are toate cerintele minime");
        student3.addTechnology(Tehnologii.Python);
        score = sistemRecomandare.calculeazaScorCompatibilitate(student3, masterAI, cerinte_medie_tehnologie);
        assertEquals(83.0, score, 0.01);

    }
}
