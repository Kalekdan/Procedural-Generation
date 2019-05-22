package main.java.com.pixolestudios.logUtils;

import main.java.com.pixolestudios.fileUtils.FileUtils;

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

    /**
     * Construct a new logger object
     * @param minLogginLvl the minimum logging level to be displayed. If this is WARNING, only warnings and worse will be logged
     */
    public Logger(LoggingLevel minLogginLvl) {
        logLvl = minLogginLvl;
    }

    /**
     * Write a message to log
     * @param input the message to log
     * @param level the logging level of the message
     */
    public void log(String input, LoggingLevel level){
        if (level.compareTo(logLvl) >= 0){
            output(level + ": " + input);
        }
    }

    /**
     * Makes the logger write logs to file instead of stdout
     * @param file the log file to write to
     * @param resetLogFile if true, will clear the log file on each new run of the application. If false, will append each application runs logs
     */
    public void writeLogsToFile(String file, boolean resetLogFile) {
        FileUtils.mkdirs(file);
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
