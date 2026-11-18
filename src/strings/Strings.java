package strings;

import java.util.Objects;

public class Strings {
    public static String requireNotBlank(String s) {
        Objects.requireNonNull(s);
        if (s.isBlank()) {
            throw new IllegalArgumentException("Input was blank");
        }
        return s;
    }

}
