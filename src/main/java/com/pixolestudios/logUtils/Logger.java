package main.java.com.pixolestudios.logUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Logger {

    private LoggingLevel logLvl;
    private boolean logsToFile = false;
    private String logFileName;

    public Logger(LoggingLevel minLogginLvl) {
        logLvl = minLogginLvl;
    }

    public void log(String input, LoggingLevel level){
        if (level.compareTo(logLvl) >= 0){
            output(level + ": " + input);
        }
    }

    public void writeLogsToFile(String file, boolean resetLogFile) {
        logsToFile = true;
        logFileName = file;
        try (FileWriter fw = new FileWriter(file, !resetLogFile); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.println("\nSTARTED LOGGING: " + LocalDate.now() + " - " + LocalTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Methods to handle the output

    private void output(String msg) {
        if (logsToFile) {
            outputFile(msg);
        } else {
            System.out.println(msg);
        }
    }

    private void outputFile(String msg) {
        try (FileWriter fw = new FileWriter(logFileName, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
