import java.text.Normalizer;
import java.util.Locale;

public enum NumeMaster {
    Automatică_și_informatică_industrială,
    Managementul_și_Protecția_Informației,
    Robotică_și_Automatizări,
    Sisteme_Informatice_în_Medicină,
    Securitate_Cibernetica,
    Data_Science,
    Jocuri_Video_Si_Realitate_Virtuala,
    Cloud_Computing,
    Inteligenta_Artificiala,
    Inginerie_Software,
    Big_Data;

    public static NumeMaster fromUserInput(String input) {
        String normalizedInput = normalize(input);
        for (NumeMaster nm : NumeMaster.values()) {
            if (normalize(nm.name()).equals(normalizedInput)) {
                return nm;
            }
        }
        throw new IllegalArgumentException("Nume master invalid");
    }

    private static String normalize(String text) {
        // Eliminam diacriticele si punem totul cu litere mici, inlocuim _ cu spatiu
        String noAccents = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replace("_", " ")
                .toLowerCase(Locale.ROOT)
                .trim();
        return noAccents;
    }
}
