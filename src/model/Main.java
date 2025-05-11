package model;

import model.Admin;
import model.CerinteMinime;
import model.CompetenteSpecifice;
import model.Facultate;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Master> mastere = new ArrayList<>();
    static Map<String, Student> conturiStudenti = new HashMap<>();
    static Map<NumeMaster, CerinteMinime> criteriiMaster = new HashMap<>();
    static SistemRecomandare sistemRecomandare = new SistemRecomandare();

    public static void main(String[] args) {
        initializeazaDatele();
        System.out.println("Bine ati venit la sistemul inteligent de admitere la master!");
        int opt = selectProgramOption("Selectati rolul:", "1. model.Student",
                "2. model.Admin", "Optiune invalida. Va rugam sa introduceti un numar." );
        if (opt != 1 && opt != 2) {
            System.out.println("Optiune invalida la inceput!");
            return;
        }
        if (opt == 1) {
            Student student = autentificareSauCreareStudent();
            int student_opt = 0;
            while (true) {
                System.out.println("\nMeniu model.Student");
                System.out.println("1. Vizualizare programe master");
                System.out.println("2. Obtine recomandari personalizate");
                System.out.println("3. Alegere program master");
                System.out.println("4. Aplicare la master");
                System.out.println("5. Adauga detalii educatie");
                System.out.println("0. Iesire");

                try {
                    student_opt = Integer.parseInt(scanner.nextLine());
                    meniuStudent(student_opt, student);
                } catch (NumberFormatException e) {
                    System.out.println("Optiune invalida. Va rugam sa introduceti un numar valid.");
                }
            }
        }
        if (opt == 2) {
            Admin admin = autentificareAdmin();
            meniuAdmin(admin);
        }
    }




    public static int selectProgramOption(String intrebare, String varianta1, String varianta2, String error) {
        int opt = 0;
        while(true) {
            System.out.println(intrebare);
            System.out.println(varianta1);
            System.out.println(varianta2);
            try {
                opt = Integer.parseInt(scanner.nextLine());
                if (opt == 1 || opt == 2) return opt;
            } catch (NumberFormatException e) {
                System.out.println(error);
            }
        }
    }

    public static String introducereEmail() {
        System.out.print("Email: ");
        while(true) {
            String mail = scanner.nextLine();
            if (conturiStudenti.containsKey(mail)) {
                return mail;
            } else {
                System.out.println("Nu exista un cont cu acest email. Doriti sa mai continuati?");
                System.out.println("Alegeti una din variantele: 'da', 'nu'");
                String answer = scanner.nextLine();
                if (answer.equals("nu")) break;
            }
        }
        System.out.println("Utilizatorul a uitat mail-ul");
        return "";
    }

    public static Student readStudentData() {
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
        double medie = 0;
        try {
            medie = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valoare invalida pentru medie. Se va folosi 0.");
        }

        return new Student(nume, mail, nrTel, dataNastere, dataAbsolvire, medie);
    }
    static void initializeazaDatele() {
        // initializam cateva programe de master
        Master intelligentalArtificiala = new Master(
                NumeMaster.Inteligenta_Artificiala,
                "Program de master focalizat pe tehnici avansate de IA si machine learning",
                EnumSet.of(Tehnologii.Python, Tehnologii.TensorFlow, Tehnologii.Machine_Learning),
                EnumSet.of(CompetenteSpecifice.Algoritmi, CompetenteSpecifice.Analiza_Datelor, CompetenteSpecifice.Limbaje_de_Programare),
                30
        );

        Master securitateCibernetica = new Master(
                NumeMaster.Securitate_Cibernetica,
                "Program de master pentru specialisti in securitatea sistemelor informatice",
                EnumSet.of(Tehnologii.Java, Tehnologii.Networking, Tehnologii.Linux),
                EnumSet.of(CompetenteSpecifice.Securitate_Informatica, CompetenteSpecifice.Networking, CompetenteSpecifice.Sisteme_de_Operare),
                25
        );

        Master inginerieSoftware = new Master(
                NumeMaster.Inginerie_Software,
                "Program de master pentru dezvoltarea aplicatiilor software complexe",
                EnumSet.of(Tehnologii.Java, Tehnologii.SQL, Tehnologii.HTML),
                EnumSet.of(CompetenteSpecifice.Baze_de_date, CompetenteSpecifice.Limbaje_de_Programare, CompetenteSpecifice.Design_Patterns),
                35
        );

        Master bigData = new Master(
                NumeMaster.Big_Data,
                "Program de master pentru analiza si gestionarea volumelor mari de date",
                EnumSet.of(Tehnologii.SQL, Tehnologii.Hadoop, Tehnologii.Spark),
                EnumSet.of(CompetenteSpecifice.Baze_de_date, CompetenteSpecifice.Analiza_Datelor, CompetenteSpecifice.Cloud_Computing),
                20
        );

        // adaugam masterele in lista
        mastere.add(intelligentalArtificiala);
        mastere.add(securitateCibernetica);
        mastere.add(inginerieSoftware);
        mastere.add(bigData);

        // initializam criteriile minime pentru fiecare master
        CerinteMinime cerinteIA = new CerinteMinime(8.5);
        for (Tehnologii tech : intelligentalArtificiala.getTehnologii()) {
            cerinteIA.adaugaTehnologieRecomandata(tech);
        }
        // Adaugăm toate competențele cerute de master
        for (CompetenteSpecifice comp : intelligentalArtificiala.getCompetente()) {
            cerinteIA.adaugaCompetentaNecesara(comp);
        }

        CerinteMinime cerinteSec = new CerinteMinime(8.0);
        for (Tehnologii tech : securitateCibernetica.getTehnologii()) {
            cerinteSec.adaugaTehnologieRecomandata(tech);
        }
        for (CompetenteSpecifice comp : securitateCibernetica.getCompetente()) {
            cerinteSec.adaugaCompetentaNecesara(comp);
        }

        CerinteMinime cerinteIS = new CerinteMinime(7.5);
        for (Tehnologii tech : inginerieSoftware.getTehnologii()) {
            cerinteIS.adaugaTehnologieRecomandata(tech);
        }
        for (CompetenteSpecifice comp : inginerieSoftware.getCompetente()) {
            cerinteIS.adaugaCompetentaNecesara(comp);
        }

        CerinteMinime cerinteBD = new CerinteMinime(8.0);
        for (Tehnologii tech : bigData.getTehnologii()) {
            cerinteBD.adaugaTehnologieRecomandata(tech);
        }
        for (CompetenteSpecifice comp : bigData.getCompetente()) {
            cerinteBD.adaugaCompetentaNecesara(comp);
        }

        // adaugam criteriile in map
        criteriiMaster.put(NumeMaster.Inteligenta_Artificiala, cerinteIA);
        criteriiMaster.put(NumeMaster.Securitate_Cibernetica, cerinteSec);
        criteriiMaster.put(NumeMaster.Inginerie_Software, cerinteIS);
        criteriiMaster.put(NumeMaster.Big_Data, cerinteBD);

        // adaugam cativa studenti predefiniti pentru testare
        Student student1 = new Student("Ana Popescu", "ana@gmail.com", "0722111222", "12.05.1999", "20.06.2023", 9.2);
        student1.addTechnology(Tehnologii.Java);
        student1.addTechnology(Tehnologii.Python);

        Student student2 = new Student("Mihai Ionescu", "mihai@gmail.com", "0733222333", "22.02.1998", "15.06.2022", 8.4);
        student2.addTechnology(Tehnologii.SQL);
        student2.addTechnology(Tehnologii.HTML);

        conturiStudenti.put(student1.getEmail(), student1);
        conturiStudenti.put(student2.getEmail(), student2);
    }

    static Student autentificareSauCreareStudent() {
        int opt2 = selectProgramOption("Aveti deja un cont?", "1. Da",
                "2. Nu", "Optiune invalida. Va rugam sa introduceti un numar." );
        if (opt2 != 1 && opt2 != 2) {
            System.out.println("Optiune invalida");
            return null;
        }
        Student student = null;
        if (opt2 == 1) {
            String email = introducereEmail();
            if (conturiStudenti.containsKey(email)) {
                System.out.println("User-ul s-a autentificat cu succes");
                return conturiStudenti.get(email);
            }
        }
        System.out.println("Nu a fost gasit un student, se va crea un cont");
        return readStudentData();
    }

    static Admin autentificareAdmin() {
            System.out.print("Nume utilizator: ");
            String nume = scanner.nextLine();
            System.out.print("Parola: ");
            String parola = scanner.nextLine();

            Admin admin = new Admin(nume, parola);
            admin.setCriteriiMaster(new HashMap<>(criteriiMaster));

            System.out.println("model.Admin autentificat cu succes.");
            return admin;

    }

    static void meniuStudent(int opt, Student student) {
            switch (opt) {
                case 1:
                    System.out.println("\nPrograme de model.Master Disponibile");
                    for (Master m : mastere) {
                        m.afisare();
                        student.vizualizareProgrameMaster(m);
                        System.out.println("----------------------------------------");
                    }
                    break;
                case 2:
                    // afisam recomandari personalizate
                    List<Master> recomandari = sistemRecomandare.recomandaProgrameMaster(student, mastere, criteriiMaster);
                    System.out.println("\nRecomandări personalizate pentru tine");
                    for (int i = 0; i < recomandari.size(); i++) {
                        Master m = recomandari.get(i);
                        double procentCompatibilitate = sistemRecomandare.getProcentCompatibilitate(m.getNume());
                        System.out.println((i+1) + ". " + m.getNume() +
                                " - Compatibilitate: " + String.format("%.1f", procentCompatibilitate) + "%");
                        System.out.println(sistemRecomandare.getSugestiiPersonalizate(student, m));
                        System.out.println("----------------------------------------");
                    }
                    break;
                case 3:
                    System.out.println("\nAlegere Program model.Master");
                    System.out.println("Selectati numarul masterului:");
                    for (int i = 0; i < mastere.size(); i++) {
                        System.out.println((i + 1) + ". " + mastere.get(i).getNume());
                    }
                    int alegere = 0;
                    try {
                        alegere = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Optiune invalida.");
                        break;
                    }

                    if (alegere >= 0 && alegere < mastere.size()) {
                        Master masterAles = mastere.get(alegere);
                        student.alegereProgramMaster(masterAles);

                        // afisam feedback personalizat despre alegere
                        System.out.println("\nAnaliza compatibilitate:");
                        System.out.println(sistemRecomandare.getSugestiiPersonalizate(student, masterAles));

                        // oferim recomandari concrete daca masterul nu este potrivit
                        if (sistemRecomandare.getProcentCompatibilitate(masterAles.getNume()) < 60) {
                            System.out.println("\nAlte programe care ar putea fi mai potrivite pentru tine:");
                            List<Master> alternativeRecomandate = sistemRecomandare.recomandaProgrameMaster(student, mastere, criteriiMaster);
                            // aratam primele 3 alternative
                            for (int i = 0; i < Math.min(3, alternativeRecomandate.size()); i++) {
                                if (!alternativeRecomandate.get(i).equals(masterAles)) {
                                    System.out.println(" - " + alternativeRecomandate.get(i).getNume());
                                }
                            }
                        }
                    } else {
                        System.out.println("Alegere invalida.");
                    }
                    break;
                case 4:
                    student.aplicaProgramMaster();
                    break;
                case 5:
                    adaugaEducatieStudent(student);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Optiune invalida!");
            }
        }

    static void adaugaEducatieStudent(Student student) {
        System.out.println("\nAdaugare Detalii Educatie");

        // adaugam facultatea absolvita
        System.out.println("1. Adauga facultatea absolvita");
        System.out.println("2. Adauga tehnologia cunoscuta");
        System.out.println("0. Inapoi la meniul principal");

        int optiune = 0;
        try {
            optiune = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Optiune invalida. Va rugam sa introduceti un numar.");
            return;
        }

        switch (optiune) {
            case 1:
                System.out.print("Nume facultate: ");
                String numeFacultate = scanner.nextLine();

                System.out.print("Domeniu (ex: Informatica, Matematica, etc.): ");
                String domeniu = scanner.nextLine();

                System.out.print("Anul absolvirii: ");
                int anAbsolvire = scanner.nextInt();

                Facultate facultate = new Facultate(numeFacultate, domeniu, anAbsolvire);
                student.adaugaFacultate(facultate);
                System.out.println("model.Facultate adaugata cu succes!");
                break;

            case 2:
                System.out.println("model.Tehnologii disponibile:");
                for (Tehnologii t : Tehnologii.values()) {
                    System.out.println(" - " + t.name().replace("_", " "));
                }

                System.out.print("Introduceti numele tehnologiei: ");
                String tehnologieInput = scanner.nextLine();

                try {
                    Tehnologii tehnologie = Tehnologii.fromUserInput(tehnologieInput);
                    student.addTechnology(tehnologie);
                    System.out.println("Tehnologie adaugata cu succes!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Tehnologie invalida!");
                }
                break;

            case 0:
                return;

            default:
                System.out.println("Optiune invalida!");
        }
    }

    static void meniuAdmin(Admin admin) {
        while (true) {
            System.out.println("\nMeniu model.Admin");
            System.out.println("1. Introducere program master");
            System.out.println("2. Introducere criterii minime");
            System.out.println("3. Afisare toate masterele");
            System.out.println("4. Afisare criterii pentru un master");
            System.out.println("0. Iesire");

            int opt = 0;
            try {
                opt = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Optiune invalida. Va rugam sa introduceti un numar.");
                continue;
            }

            switch (opt) {
                case 1:
                    System.out.println("\nIntroducere Program model.Master");
                    System.out.println("Nume master disponibile:");
                    for (NumeMaster nm : NumeMaster.values()) {
                        System.out.println(" - " + nm.name().replace("_", " "));
                    }

                    System.out.print("Nume master: ");
                    NumeMaster numeMaster = null;
                    while (numeMaster == null) {
                        try {
                            String input = scanner.nextLine();
                            numeMaster = NumeMaster.fromUserInput(input);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Nume invalid. Te rugam sa incerci din nou:");
                        }
                    }

                    System.out.print("Descriere: ");
                    String descriere = scanner.nextLine();

                    // adaugare tehnologii
                    EnumSet<Tehnologii> tehnologii = EnumSet.noneOf(Tehnologii.class);
                    System.out.println("model.Tehnologii disponibile:");
                    for (Tehnologii t : Tehnologii.values()) {
                        System.out.println(" - " + t.name().replace("_", " "));
                    }

                    System.out.println("Adaugati tehnologii (scrieti END pentru a opri):");
                    while (true) {
                        String t = scanner.nextLine();
                        if (t.equalsIgnoreCase("END")) break;
                        try {
                            tehnologii.add(Tehnologii.fromUserInput(t));
                            System.out.println("Tehnologie adaugata!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Tehnologie invalida!");
                        }
                    }

                    // adaugare competente
                    EnumSet<CompetenteSpecifice> competente = EnumSet.noneOf(CompetenteSpecifice.class);
                    System.out.println("Competențe disponibile:");
                    for (CompetenteSpecifice c : CompetenteSpecifice.values()) {
                        System.out.println(" - " + c.name().replace("_", " "));
                    }

                    System.out.println("Adaugati competente (scrieti END pentru a opri):");
                    while (true) {
                        String c = scanner.nextLine();
                        if (c.equalsIgnoreCase("END")) break;
                        try {
                            competente.add(CompetenteSpecifice.fromUserInput(c));
                            System.out.println("Competenta adaugata!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Competenta invalida!");
                        }
                    }

                    System.out.print("Numar maxim de studenti: ");
                    int nrMax = 0;
                    try {
                        nrMax = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Valoare invalida. Se va folosi 30 ca valoare implicita.");
                        nrMax = 30;
                    }

                    // verificam dacă masterul exista deja
                    boolean exists = false;
                    for (Master m : mastere) {
                        if (m.getNume() == numeMaster) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Un program de master cu acest nume exista deja!");
                    } else {
                        // crearea obiectului model.Master
                        Master master = new Master(numeMaster, descriere, tehnologii, competente, nrMax);
                        mastere.add(master);
                        admin.introducereProgrameMaster(master);
                        System.out.println("Program master adaugat cu succes!");
                    }
                    break;

                case 2:
                    System.out.println("\nIntroducere Criterii Minime");
                    admin.introducereCerinteMinime();
                    // actualizam criteriile din sistemul principal
                    criteriiMaster.putAll(admin.getCriteriiMaster());
                    break;

                case 3:
                    System.out.println("\nToate Programele de model.Master");
                    if (mastere.isEmpty()) {
                        System.out.println("Nu exista programe de master introduse!");
                    } else {
                        for (Master m : mastere) {
                            m.afisare();
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nAfisare Criterii pentru model.Master");
                    System.out.println("Selectati masterul:");
                    for (int i = 0; i < mastere.size(); i++) {
                        System.out.println((i + 1) + ". " + mastere.get(i).getNume());
                    }

                    int indexMaster = 0;
                    try {
                        indexMaster = Integer.parseInt(scanner.nextLine()) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Optiune invalida.");
                        break;
                    }

                    if (indexMaster >= 0 && indexMaster < mastere.size()) {
                        NumeMaster master = mastere.get(indexMaster).getNume();
                        admin.afisareCerintePentruMaster(master);
                    } else {
                        System.out.println("Selectie invalida!");
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