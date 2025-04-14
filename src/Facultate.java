public class Facultate {
    private String nume;
    private String domeniu;
    private Uni uni;

    public Facultate(String nume, String domeniu, Uni uni) {
        this.nume = nume;
        this.domeniu = domeniu;
        this.uni = uni;
    }

    public void afisare() {
        System.out.println("Nume Facultate: " + nume);
        System.out.println("Nume domeniu: " + domeniu);
        System.out.println("Nume universitate: " + uni);
    }
}
