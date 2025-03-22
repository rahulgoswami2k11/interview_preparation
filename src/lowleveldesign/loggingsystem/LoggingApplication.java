package lowleveldesign.loggingsystem;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoggingApplication {
    public static void main(String[] args) throws InterruptedException {
        LoggerConfig loggerConfig = new LoggerConfig(
                LogLevel.DEBUG,
                new FileLogAppender(new File("./src/lowleveldesign/loggingsystem/logfile.txt"))
        );
        Logger logger = Logger.getLogger(loggerConfig);

        ExecutorService service = Executors.newFixedThreadPool(100);
        for(int i=0; i<1000; i++) {
            service.submit(() -> {
                logger.log(LogLevel.INFO, "This is my first log message");
            });
        }

        service.shutdown();
    }
}
