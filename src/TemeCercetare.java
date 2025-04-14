public class TemeCercetare {
    private String nume;
    private String descriere;
    private Tehnologii tehnologii;
    private int numarStudenti;

    public TemeCercetare(String nume, String descriere, Tehnologii tehnologii, int numarStudenti) {
        this.nume = nume;
        this.descriere = descriere;
        this.tehnologii = tehnologii;
        this.numarStudenti = numarStudenti;
    }

    public void afisare() {
        System.out.println("Nume tema cercetare: " + nume);
        System.out.println("Descriere tema cercetare: " + descriere);
        System.out.println("Tehnologii utilizate: " + tehnologii);
        System.out.println("Numar studenti: " + numarStudenti);
    }
}
