import java.text.Normalizer;
import java.util.Locale;

public enum CompetenteSpecifice {
    Limbaje_de_Programare,
    Sisteme_informatice,
    Arhitecturi_hardware,
    Arhitecturi_software,
    Prelucrarea_Semnalelor,
    Sisteme_de_Operare,
    Rețele_de_Calculatoare;


    public static CompetenteSpecifice fromUserInput(String input) {
        String normalized = normalize(input);

        for (CompetenteSpecifice c : values()) {
            if (normalize(c.name()).equals(normalized)) {
                return c;
            }
        }

        throw new IllegalArgumentException("Competenta invalida");
    }

    private static String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "") // elimină diacriticele
                .replace("_", "")
                .replace("-", "")
                .replace(" ", "")
                .toLowerCase(Locale.ROOT)
                .trim();
    }
}
