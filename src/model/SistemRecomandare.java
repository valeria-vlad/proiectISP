package model;

import model.CerinteMinime;
import model.Facultate;
import model.Master;
import model.NumeMaster;

import java.util.*;

public class SistemRecomandare {
    private Map<NumeMaster, Double> scoruriCompatibilitate = new HashMap<>();

    // analizam profilul studentului si generam recomandari
    public List<Master> recomandaProgrameMaster(Student student, List<Master> programeDisponibile,
                                                Map<NumeMaster, CerinteMinime> cerinte) {
        scoruriCompatibilitate.clear();

        for (Master program : programeDisponibile) {
            double scor = calculeazaScorCompatibilitate(student, program, cerinte.get(program.getNume()));
            scoruriCompatibilitate.put(program.getNume(), scor);
        }

        // sortam programele dupa scorul de compatibilitate
        List<Master> recomandari = new ArrayList<>(programeDisponibile);
        recomandari.sort((m1, m2) -> Double.compare(
                scoruriCompatibilitate.getOrDefault(m2.getNume(), 0.0),
                scoruriCompatibilitate.getOrDefault(m1.getNume(), 0.0)
        ));

        return recomandari;
    }

    private double calculeazaScorCompatibilitate(Student student, Master program, CerinteMinime cerinte) {
        double scor = 0.0;

        // verificam dacă programul are cerinte minime definite
        if (cerinte == null) {
            return evaluareImplicita(student, program);
        }

        // primul factor: media studentului relativ la cerinta minima (40% din scor)
        // cu cat media e mai mare peste cerinta minima, cu atat scorul creste
        if (student.getMedieFacultate() >= cerinte.getMedieMinima()) {
            double diferentaMedie = student.getMedieFacultate() - cerinte.getMedieMinima();
            scor += 40 + Math.min(diferentaMedie * 10, 20); // max 60 puncte pentru medie
        }

        // al doilea factor: overlap intre tehnologiile studentului si cele recomandate (25% din scor)
        int tehnologiiPotrivite = 0;
        for (Tehnologii t : student.getTechnologies()) {
            if (cerinte.getTehnologiiRecomandate().contains(t)) {
                tehnologiiPotrivite++;
            }
        }

        if (!cerinte.getTehnologiiRecomandate().isEmpty()) {
            double procentTehnologii = (double) tehnologiiPotrivite / cerinte.getTehnologiiRecomandate().size();
            scor += procentTehnologii * 25; // maxim 25 puncte pentru tehnologii
        }

        // al treilea factor: preferinte anterioare ale studentului (15% din scor)
        if (student.getMastereVizualizate().contains(program)) {
            scor += 15; // puncte pentru interes demonstrat
        }

        // al patrulea factor: istoricul academic (facultatile absolvite) (10% din scor)
        // verificam daca facultatea se potriveste cu masterul
        for (Facultate f : student.getFacultati()) {
            if (f.getDomeniu().toLowerCase().contains(program.getNume().name().toLowerCase())) {
                scor += 10;
                break;
            }
        }

        return scor;
    }

    private double evaluareImplicita(Student student, Master program) {
        // O evalare simplă când nu există cerințe specifice
        double scor = 50; // Scor de bază

        // Ajustare bazată pe mastere vizualizate anterior
        if (student.getMastereVizualizate().contains(program)) {
            scor += 20;
        }

        return scor;
    }

    // adaugam metoda pentru a obtine procentul de compatibilitate
    public double getProcentCompatibilitate(NumeMaster numeMaster) {
        return scoruriCompatibilitate.getOrDefault(numeMaster, 0.0);
    }

    public String getSugestiiPersonalizate(Student student, Master program) {
        double scor = scoruriCompatibilitate.getOrDefault(program.getNume(), 0.0);

        if (scor >= 80) {
            return "Acest program este foarte potrivit pentru tine bazat pe profilul tau academic!";
        } else if (scor >= 60) {
            return "Acest program este potrivit pentru tine, dar ar putea necesita efort suplimentar in anumite domenii.";
        } else if (scor >= 40) {
            return "Acest program este o alegere posibila, dar nu ideala pentru experienta ta actuala.";
        } else {
            return "Acest program nu pare sa fie cel mai potrivit pentru profilul tau. Considera alte optiuni.";
        }
    }
}