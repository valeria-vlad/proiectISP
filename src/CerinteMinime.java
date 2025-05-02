import java.util.EnumSet;

public class CerinteMinime {
    private double medieMinima;
    private EnumSet<Tehnologii> tehnologiiRecomandate;
    private EnumSet<CompetenteSpecifice> competenteNecesar;

    public CerinteMinime(double medie, EnumSet<Tehnologii> tehnologii, EnumSet<CompetenteSpecifice> competente) {
        this.medieMinima = medie;
        this.tehnologiiRecomandate = tehnologii;
        this.competenteNecesar = competente;
    }

    @Override
    public String toString() {
        return "Medie minima: " + medieMinima +
                "\nTehnologii recomandate: " + tehnologiiRecomandate +
                "\nCompetente necesare: " + competenteNecesar;
    }
}
