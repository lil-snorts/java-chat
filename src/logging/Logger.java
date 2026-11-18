package logging;

import strings.Strings;

import java.util.Arrays;

public class Logger {

    private static final char DOUBLE_QUOTE = '"';

    protected enum Level {
        DEBUG, INFO, WARN, ERROR
    }

    private final String name;

    public Logger(String name) {
        this.name = Strings.requireNotBlank(name);
    }

    public void debug(String message) {
        this.log(() -> message, Level.DEBUG);
    }

    public void info(Loggable loggableMessage) {
        this.log(loggableMessage, Level.INFO);
    }

    public void info(Loggable loggableMessage, LogPair... varargs) {
        this.log(loggableMessage, Level.INFO, varargs);
    }

    public void error(Loggable loggable, LogPair... varargs) {
        this.log(loggable, Level.ERROR, varargs);
    }

    protected void log(Loggable loggableMessage, Level level, LogPair... varargs) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        appendKeyVal(sb, "level", level.name());
        appendKeyVal(sb, "logger", this.name);
        appendKeyVal(sb, "thread", Thread.currentThread().getName());
        appendKeyVal(sb, "message", loggableMessage.message());
         Arrays.stream(varargs)
                .map(LogPair::toString)
                .reduce((a, b) -> a + ", " + b)
                .ifPresent(it -> appendKeyVal(sb, "args", it));
        sb.append('}');
        System.out.println(sb);
    }

    private void appendKeyVal(StringBuilder sb, String arg, String value) {
        sb.append(DOUBLE_QUOTE).append(arg).append(DOUBLE_QUOTE).append(":")
                .append(DOUBLE_QUOTE).append(value).append(DOUBLE_QUOTE).append(',');
    }
}
