package lowleveldesign.loggingsystem;

public class ConsoleLogAppender implements LogAppender {
    @Override
    public void log(LogMessage message) {
        System.out.println(message);
    }
}
