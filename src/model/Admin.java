package model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {
    private String numeUtilizator;
    private String parola;

    private Map<NumeMaster, CerinteMinime> criteriiMaster = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public Admin(String numeUtilizator, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
    }

    // Getter pentru criteriiMaster
    public Map<NumeMaster, CerinteMinime> getCriteriiMaster() {
        return criteriiMaster;
    }

    // Setter pentru criteriiMaster - adăugat nou
    public void setCriteriiMaster(Map<NumeMaster, CerinteMinime> criteriiMaster) {
        this.criteriiMaster = criteriiMaster;
    }

    public void introducereProgrameMaster(Master m) {
        System.out.println("Adminul a introdus masterul " + m.getNume());
    }

    public void introducereCerinteMinime() {
        System.out.println("Introduceti programul de master pentru care doriti sa introduceti criterii minime:");
        for (NumeMaster master : NumeMaster.values()) {
            System.out.println(" - " + master.name().replace("_", " "));
        }

        NumeMaster master = null;
        while (master == null) {
            try {
                String input = scanner.nextLine();
                master = NumeMaster.fromUserInput(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Nume invalid. Reintroduceti:");
            }
        }

        System.out.print("Introduceti media minima: ");
        double medie = 0.0;
        while (true) {
            try {
                medie = Double.parseDouble(scanner.nextLine());
                if (medie < 0 || medie > 10) {
                    System.out.println("Media trebuie sa fie intre 0 si 10. Reintroduceti:");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valoare invalida. Reintroduceti media:");
            }
        }

        EnumSet<Tehnologii> tehnologii = EnumSet.noneOf(Tehnologii.class);
        System.out.println("Introduceti tehnologii recomandate (scrieti END pentru a opri):");
        while (true) {
            String t = scanner.nextLine();
            if (t.equalsIgnoreCase("END")) break;
            try {
                tehnologii.add(Tehnologii.fromUserInput(t));
            } catch (IllegalArgumentException e) {
                System.out.println("Tehnologie invalida!");
            }
        }

        EnumSet<CompetenteSpecifice> competente = EnumSet.noneOf(CompetenteSpecifice.class);
        System.out.println("Introduceti competente necesare (scrieti END pentru a opri):");
        while (true) {
            String c = scanner.nextLine();
            if (c.equalsIgnoreCase("END")) break;
            try {
                competente.add(CompetenteSpecifice.fromUserInput(c));
            } catch (IllegalArgumentException e) {
                System.out.println("Competenta invalida!");
            }
        }

        CerinteMinime cerinteMinime = new CerinteMinime(medie);
        for (Tehnologii tehnologie : tehnologii) {
            cerinteMinime.adaugaTehnologieRecomandata(tehnologie);
        }
        for (CompetenteSpecifice competenta : competente) {
            cerinteMinime.adaugaCompetentaNecesara(competenta);
        }

        criteriiMaster.put(master, cerinteMinime);
        System.out.println("Criteriile minime pentru " + master.name().replace("_", " ") + " au fost salvate.");
    }

    public void afisareCerintePentruMaster(NumeMaster master) {
        CerinteMinime cerinte = criteriiMaster.get(master);
        if (cerinte != null) {
            System.out.println("Criterii pentru " + master.name().replace("_", " ") + ":");
            System.out.println(cerinte);
        } else {
            System.out.println("Nu exista criterii introduse pentru acest master.");
        }
    }
}