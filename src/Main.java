public class Main {
    public static void main(String[] args) {
        Master master = new Master(NumeMaster.Robotică_și_Automatizări, "Acest master este dedicat pasionatilor de robotica", Tehnologii.Arduino, CompetenteSpecifice.Sisteme_informatice, 30);
        master.afisare();

        Student student = new Student("Popa Mihai", "mihai.popa@gmail.com", "0752725356", "28-09-2001", "24-07-2024", 9.67);
        student.vizualizareProgrameMaster(master);
    }
}