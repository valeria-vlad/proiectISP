public class Master {
    private NumeMaster nume;
    private String descriere;
    private Tehnologii tehnologiiFolosite;
    private CompetenteSpecifice competenteSpecifice;
    private int numarMaximStudenti;

    public Master(NumeMaster nume, String descriere, Tehnologii tehnologiiFolosite, CompetenteSpecifice competenteSpecifice, int numarMaximStudenti) {
        this.nume = nume;
        this.descriere = descriere;
        this.tehnologiiFolosite = tehnologiiFolosite;
        this.competenteSpecifice = competenteSpecifice;
        this.numarMaximStudenti = numarMaximStudenti;
    }

    public NumeMaster getNume() {
        return nume;
    }

    public void afisare() {
        System.out.println("Nume Master: " + nume);
        System.out.println("Descriere master: " + descriere);
        System.out.println("Tehnologii utilizate: " + tehnologiiFolosite);
        System.out.println("Competente specifice: " + competenteSpecifice);
        System.out.println("Numar maxim de studenti: " + numarMaximStudenti);
    }
}
