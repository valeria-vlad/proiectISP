import java.util.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Master> mastere = new ArrayList<>();
    static Map<String, Student> conturiStudenti = new HashMap<>();

    public static void main(String[] args) {

        mastere.add(new Master(
                NumeMaster.Automatică_și_informatică_industrială,
                "Descriere program 1",
                EnumSet.noneOf(Tehnologii.class),
                EnumSet.noneOf(CompetenteSpecifice.class),
                50
        ));

        mastere.add(new Master(
                NumeMaster.Managementul_și_Protecția_Informației,
                "Descriere program 2",
                EnumSet.of(Tehnologii.Java, Tehnologii.C_plus_plus),
                EnumSet.of(CompetenteSpecifice.Limbaje_de_Programare),
                30
        ));

        System.out.println("Bine ati venit la sistemul de admitere la master!");
        System.out.println("Selectati rolul:");
        System.out.println("1. Student");
        System.out.println("2. Admin");
        int opt = Integer.parseInt(scanner.nextLine());

        if (opt == 1) {
            Student student = autentificareSauCreareStudent();
            meniuStudent(student);
        } else if (opt == 2) {
            Admin admin = autentificareAdmin();
            meniuAdmin(admin);
        } else {
            System.out.println("Optiune invalida!");
        }
    }

    static Student autentificareSauCreareStudent() {
        System.out.println("Aveti deja un cont?");
        System.out.println("1. Da");
        System.out.println("2. Nu");
        int opt = Integer.parseInt(scanner.nextLine());

        if (opt == 1) {
            System.out.print("Email: ");
            String mail = scanner.nextLine();
            if (conturiStudenti.containsKey(mail)) {
                System.out.println("Autentificare reusita!");
                return conturiStudenti.get(mail);
            } else {
                System.out.println("Nu exista un cont cu acest email.");
                return autentificareSauCreareStudent();
            }
        } else if (opt == 2) {
            System.out.print("Nume: ");
            String nume = scanner.nextLine();
            System.out.print("Email: ");
            String mail = scanner.nextLine();
            System.out.print("Telefon: ");
            String nrTel = scanner.nextLine();
            System.out.print("Data nasterii: ");
            String dataNastere = scanner.nextLine();
            System.out.print("Data absolvire: ");
            String dataAbsolvire = scanner.nextLine();
            System.out.print("Medie facultate: ");
            double medie = Double.parseDouble(scanner.nextLine());

            Student student = new Student(nume, mail, nrTel, dataNastere, dataAbsolvire, medie);
            conturiStudenti.put(mail, student);
            student.autentificare();
            System.out.println("Cont creat si autentificat cu succes!");
            return student;
        } else {
            System.out.println("Optiune invalida.");
            return autentificareSauCreareStudent();
        }
    }

    static Admin autentificareAdmin() {
        System.out.print("Nume utilizator: ");
        String nume = scanner.nextLine();
        System.out.print("Parola: ");
        String parola = scanner.nextLine();

        Admin admin = new Admin(nume, parola);
        System.out.println("Admin autentificat cu succes.");
        return admin;
    }

    static void meniuStudent(Student student) {
        while (true) {
            System.out.println("\n Meniu Student");
            System.out.println("1. Vizualizare programe master");
            System.out.println("2. Alegere program master");
            System.out.println("3. Aplicare la master");
            System.out.println("0. Iesire");
            int opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1:
                    for (Master m : mastere) {
                        m.afisare();
                        student.vizualizareProgrameMaster(m);
                    }
                    break;
                case 2:
                    System.out.println("Selectati numarul masterului:");
                    for (int i = 0; i < mastere.size(); i++) {
                        System.out.println((i + 1) + ". " + mastere.get(i).getNume());
                    }
                    int alegere = Integer.parseInt(scanner.nextLine()) - 1;
                    if (alegere >= 0 && alegere < mastere.size()) {
                        student.alegereProgramMaster(mastere.get(alegere));
                    } else {
                        System.out.println("Alegere invalida.");
                    }
                    break;
                case 3:
                    student.aplicaProgramMaster();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }

    static void meniuAdmin(Admin admin) {
        while (true) {
            System.out.println("\nMeniu Admin");
            System.out.println("1. Introducere program master");
            System.out.println("2. Introducere criterii minime");
            System.out.println("3. Afisare toate masterele");
            System.out.println("0. Iesire");
            int opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1:
                    System.out.print("Nume master (AUTOMATICA_INFORMATICA / MANAGEMENTUL_SI_PROTECTIA / ROBOTICA_AUTOMATIZARI / SISTEME_MEDICALE): ");
                    NumeMaster numeMaster = null;
                    while (numeMaster == null) {
                        try {
                            numeMaster = NumeMaster.valueOf(scanner.nextLine().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Nume invalid. Te rugam sa incerci din nou:");
                        }
                    }

                    System.out.print("Descriere: ");
                    String descriere = scanner.nextLine();

                    // Adaugare tehnologii
                    EnumSet<Tehnologii> tehnologii = EnumSet.noneOf(Tehnologii.class);
                    System.out.println("Adaugati tehnologii (scrieti END pentru a opri):");
                    while (true) {
                        String t = scanner.nextLine();
                        if (t.equalsIgnoreCase("END")) break;
                        try {
                            tehnologii.add(Tehnologii.valueOf(t.toUpperCase())); // Transformam String in enum
                        } catch (IllegalArgumentException e) {
                            System.out.println("Tehnologie invalida!");
                        }
                    }

                    // Adaugare competente
                    EnumSet<CompetenteSpecifice> competente = EnumSet.noneOf(CompetenteSpecifice.class);
                    System.out.println("Adaugati competente (scrieti END pentru a opri):");
                    while (true) {
                        String c = scanner.nextLine();
                        if (c.equalsIgnoreCase("END")) break;
                        try {
                            competente.add(CompetenteSpecifice.valueOf(c.toUpperCase())); // Transformare String in enum
                        } catch (IllegalArgumentException e) {
                            System.out.println("Competenta invalida!");
                        }
                    }

                    System.out.print("Numar maxim de studenti: ");
                    int nrMax = Integer.parseInt(scanner.nextLine());

                    // Crearea obiectului Master
                    Master master = new Master(numeMaster, descriere, tehnologii, competente, nrMax);
                    mastere.add(master);
                    admin.introducereProgrameMaster(master);
                    break;

                case 2:
                    admin.introducereCerinteMinime();
                    break;
                case 3:
                    for (Master mastereExistente : mastere) {
                        mastereExistente.afisare();
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}