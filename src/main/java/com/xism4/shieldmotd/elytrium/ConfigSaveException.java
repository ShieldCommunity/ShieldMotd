package com.xism4.shieldmotd.elytrium;

/**
 * Original https://github.com/Elytrium/java-commons
 */
public class ConfigSaveException extends RuntimeException {

    public ConfigSaveException(Throwable cause) {
        this("An unexpected internal error was caught during saving the config.", cause);
    }

    public ConfigSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}