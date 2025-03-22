package lowleveldesign.loggingsystem;

public class Logger {
    private static Logger _logger = null;
    private LoggerConfig loggerConfig;

    private Logger(LoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    public static Logger getLogger(LoggerConfig loggerConfig) {
        if(_logger == null) {
            synchronized (Logger.class) {
                if(_logger == null) {
                    _logger = new Logger(loggerConfig);
                }
            }
        }

        return _logger;
    }

    void log(LogLevel logLevel, String message) {
        if (logLevel.ordinal() >= this.loggerConfig.logLevel().ordinal()) {
            LogMessage logMessage = new LogMessage(logLevel, message);
            loggerConfig.logAppender().log(logMessage);
        }
    }

}
