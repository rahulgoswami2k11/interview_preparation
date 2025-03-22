package lowleveldesign.loggingsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileLogAppender implements LogAppender {

    File logFile;

    FileLogAppender(File logFile) {
        this.logFile = logFile;
    }

    @Override
    public void log(LogMessage message) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true))) {
            bufferedWriter.append(message.toString());
            bufferedWriter.newLine();
        } catch (Exception e) {
            System.out.println("Failed to write logs to file");
        }
    }
}
