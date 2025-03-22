package lowleveldesign.loggingsystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogMessage {
    private final LogLevel logLevel;
    private final String message;
    private final long timestamp;

    public LogMessage(LogLevel logLevel, String message) {
        this.logLevel = logLevel;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return timestamp + " : [" + logLevel + "] " + message;
    }
}
