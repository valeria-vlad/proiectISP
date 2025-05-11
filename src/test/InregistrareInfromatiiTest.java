package test;

import model.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.sound.sampled.Port;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.assertEquals;
public class InregistrareInfromatiiTest {
    Student student1 = new Student("Maria Rec", "mariar@mail.com", "1112223334", "01-01-2001", "12-06-2025", 8.98);
    Tehnologii tehn1 = Tehnologii.Java;
    Tehnologii tehn2 = Tehnologii.Arduino;
    Tehnologii tehn3 = Tehnologii.HTML;
    Tehnologii tehn4 = Tehnologii.C_plus_plus;
    @Test
    void testInregistrareStudent(){
        assertEquals(student1.getNume(), "Maria Rec");
        assertEquals(student1.getEmail(), "mariar@mail.com");
        assertEquals(student1.getMedieFacultate(), 8.98);
    }

    void testInregFacultate(){
        Facultate facultate1 = new Facultate("Automatica", "Ingineria Sistemelor", 2025);
        student1.adaugaFacultate(facultate1);
        assertEquals(student1.getFacultati().get(0), facultate1);
    }

    void testInregTehn(){
        student1.addTechnology(tehn1);
        student1.addTechnology(tehn2);
        student1.addTechnology(tehn3);
        assertEquals(student1.getTechnologies().get(0), tehn1);
        assertEquals(student1.getTechnologies().get(1), tehn2);
        assertEquals(student1.getTechnologies().get(2), tehn3);

    }

    void testInregEd(){
        Facultate facultate2 = new Facultate("Automatica", "Programare", 2025);
        student1.addInformatiiEducatie(facultate2, tehn4);
        assertEquals(student1.getFacultati().get(1), facultate2);
        assertEquals(student1.getTechnologies().get(3), tehn4);
    }
}
