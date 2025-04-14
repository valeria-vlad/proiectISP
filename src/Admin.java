public class Admin {
    private String numeUtilizator;
    private String parola;

    public Admin(String numeUtilizator, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
    }

    public void introducereProgrameMaster(Master m) {
        System.out.println("Adminul a introdus masterul " + m.getNume());
    }

    public void introducereCerinteMinime() {
        System.out.println("Adminul a introdus criteriile minime pentru admitere.");
    }
}
