package model;

import model.Facultate;
import model.Master;
import model.ProfesorCoordonator;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String nume;
    private String email;
    private String nrTelefon;
    private String dataNasterii;
    private String dataAbsolvirii;
    private double medieFacultate;

    public Student(String nume, String email, String nrTelefon, String dataNasterii, String dataAbsolvirii, double medieFacultate) {
        this.nume = nume;
        this.email = email;
        this.nrTelefon = nrTelefon;
        this.dataNasterii = dataNasterii;
        this.dataAbsolvirii = dataAbsolvirii;
        this.medieFacultate = medieFacultate;
    }

    public String getNume() {
        return nume;
    }

    public double getMedieFacultate() {
        return medieFacultate;
    }

    private List<Facultate> facultati = new ArrayList<>();
    private List<ProfesorCoordonator> profesoriCoordonatori = new ArrayList<>();
    private List<Master> mastereVizualizate = new ArrayList<>();
    private List<Tehnologii> tehnologii = new ArrayList<>();
    private Master masterAles;

    // Getter pentru listă de facultăți
    public List<Facultate> getFacultati() {
        return facultati;
    }

    // Getter pentru listă de tehnologii
    public List<Tehnologii> getTechnologies() {
        return tehnologii;
    }

    // Getter pentru mastere vizualizate
    public List<Master> getMastereVizualizate() {
        return mastereVizualizate;
    }

    public void adaugaFacultate(Facultate facultate) {
        facultati.add(facultate);
    }

    public void addTechnology(Tehnologii tehnologie) {
        tehnologii.add(tehnologie);
    }

    public void addInformatiiEducatie(Facultate f, Tehnologii t) {
        facultati.add(f);
        tehnologii.add(t);
    }

    public void addPreferinte(ProfesorCoordonator p, Master m) {
        profesoriCoordonatori.add(p);
        mastereVizualizate.add(m);
    }

    public void vizualizareProgrameMaster(Master m) {
        mastereVizualizate.add(m);
        System.out.println("model.Master vizualizat: " + m.getNume() +  " - " + m.getDescriere() );
    }

    public void alegereProgramMaster(Master m) {
        masterAles = m;
        System.out.println("model.Master ales: " + m.getNume());
    }

    public void autentificare() {
        System.out.println("model.Student " + nume + " s-a autentificat cu emailul: " + email);
    }

    public void aplicaProgramMaster() {
        if(masterAles != null) {
            System.out.println(nume + " a aplicat la masterul: " + masterAles.getNume());
        }
        else {
            System.out.println("Nu s-a ales niciun master");
        }
    }
}