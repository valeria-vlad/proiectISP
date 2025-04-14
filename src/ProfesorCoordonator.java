import java.util.ArrayList;
import java.util.List;

public class ProfesorCoordonator {
    private String nume;
    private NumeMaster master;
    private String numarTelefon;
    private String email;

    public ProfesorCoordonator(String nume, NumeMaster master, String numarTelefon, String email) {
        this.nume = nume;
        this.master = master;
        this.numarTelefon = numarTelefon;
        this.email = email;
    }

    List<CompetenteSpecifice> competenteSpecifice = new ArrayList<>();

    public void introducereCompetente(CompetenteSpecifice c) {
        competenteSpecifice.add(c);
    }

    public void selectareStudenti(Student s) {
        System.out.println("Profesorul " + nume + " a selectat studentul " + s.getNume());
    }
}
