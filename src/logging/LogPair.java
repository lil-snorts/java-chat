package logging;

import strings.Strings;

public record LogPair(String key, String value) {
    public LogPair {
        Strings.requireNotBlank(key);
    }

    public static LogPair of(String key, int value) {
        return new LogPair(key, String.valueOf(value));
    }

    public static LogPair of(String key, String value) {
        return new LogPair(key, value);
    }

    @Override
    public String toString() {
        return "'" + key + '=' + value + '\'';
    }
}