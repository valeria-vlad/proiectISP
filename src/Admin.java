import java.util.*;

public class Admin {
    private String numeUtilizator;
    private String parola;

    private Map<NumeMaster, CerinteMinime> criteriiMaster = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public Admin(String numeUtilizator, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
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
                tehnologii.add(Tehnologii.valueOf(t));
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
                competente.add(CompetenteSpecifice.valueOf(c));
            } catch (IllegalArgumentException e) {
                System.out.println("Competenta invalida!");
            }
        }

        criteriiMaster.put(master, new CerinteMinime(medie, tehnologii, competente));
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
