package model;

import java.util.EnumSet;

public class CerinteMinime {
    private double medieMinima;
    private EnumSet<Tehnologii> tehnologiiRecomandate;
    private EnumSet<CompetenteSpecifice> competenteNecesar;

    public CerinteMinime(double medie) {
        this.medieMinima = medie;
        this.tehnologiiRecomandate = EnumSet.noneOf(Tehnologii.class);
        this.competenteNecesar = EnumSet.noneOf(CompetenteSpecifice.class); // Inițializăm și acest EnumSet
    }

    public double getMedieMinima() {
        return medieMinima;
    }

    public void adaugaTehnologieRecomandata(Tehnologii tehnologie) {
        this.tehnologiiRecomandate.add(tehnologie);
    }

    public void adaugaCompetentaNecesara(CompetenteSpecifice competenta) {
        this.competenteNecesar.add(competenta);
    }

    public EnumSet<Tehnologii> getTehnologiiRecomandate() {
        return tehnologiiRecomandate;
    }

    public EnumSet<CompetenteSpecifice> getCompetenteNecesar() {
        return competenteNecesar;
    }

    public EnumSet<CompetenteSpecifice> getCompetenteNecesare() {
        return competenteNecesar;
    }
    @Override
    public String toString() {
        return "Medie minima: " + medieMinima +
                "\nTehnologii recomandate: " + tehnologiiRecomandate +
                "\nCompetente necesare: " + competenteNecesar;
    }
}