import java.text.Normalizer;
import java.util.Locale;

public enum Tehnologii {
    Java,
    Python,
    C_plus_plus,
    Matlab,
    Simulink,
    Verilog,
    Arduino,
    Linux,
    punct_net,
    UML,
    Cloud,
    Blockchain;

    public static Tehnologii fromUserInput(String input) {
        String normalized = normalize(input);

        switch (normalized) {
            case "c++":
            case "cplusplus":
                return C_plus_plus;
            case ".net":
            case "dotnet":
            case "net":
                return punct_net;
        }

        for (Tehnologii t : values()) {
            if (normalize(t.name()).equals(normalized)) {
                return t;
            }
        }

        throw new IllegalArgumentException("Tehnologie invalida");
    }

    private static String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replace("_", "")
                .replace("-", "")
                .toLowerCase(Locale.ROOT)
                .trim();
    }
}
