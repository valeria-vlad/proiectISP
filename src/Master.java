import java.util.EnumSet;

public class Master {
    private NumeMaster numeMaster;
    private String descriere;
    private EnumSet<Tehnologii> tehnologii;
    private EnumSet<CompetenteSpecifice> competente;
    private int nrMax;

    // Constructor
    public Master(NumeMaster numeMaster, String descriere, EnumSet<Tehnologii> tehnologii, EnumSet<CompetenteSpecifice> competente, int nrMax) {
        this.numeMaster = numeMaster;
        this.descriere = descriere;
        this.tehnologii = tehnologii;
        this.competente = competente;
        this.nrMax = nrMax;
    }

    public NumeMaster getNume() {
        return numeMaster;
    }

    public String getDescriere() {
        return descriere;
    }

    public void afisare() {
        System.out.println("Program Master: " + numeMaster);
        System.out.println("Descriere: " + descriere);
        System.out.println("Tehnologii: " + tehnologii);
        System.out.println("Competențe: " + competente);
        System.out.println("Număr maxim de studenți: " + nrMax);
    }
}
