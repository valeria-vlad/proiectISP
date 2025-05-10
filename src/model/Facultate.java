package model;

public class Facultate {
    private String nume;
    private String domeniu;
    private Uni uni;
    private int anAbsolvire;

    public Facultate(String nume, String domeniu, int anAbsolvire) {
        this.nume = nume;
        this.domeniu = domeniu;
        this.anAbsolvire = anAbsolvire;
    }

    public String getDomeniu() {
        return domeniu;
    }

    public void afisare() {
        System.out.println("Nume model.Facultate: " + nume);
        System.out.println("Nume domeniu: " + domeniu);
        System.out.println("Nume universitate: " + uni);
    }
}