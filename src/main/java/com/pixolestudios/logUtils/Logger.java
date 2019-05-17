package main.java.com.pixolestudios.logUtils;

public class Logger {

    private LoggingLevel logLvl;

    public Logger(LoggingLevel minLogginLvl) {
        logLvl = minLogginLvl;
    }

    public void log(String input, LoggingLevel level){
        if (level.compareTo(logLvl) >= 0){
            System.out.println(level + ": " + input);
        }
    }
}
