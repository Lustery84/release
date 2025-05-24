package hust.soict.dsai.aims.exception;

public class PlayerException extends Exception { // [cite: 211]

    public PlayerException() { // [cite: 211]
        super();
    }

    public PlayerException(String message) { // [cite: 211]
        super(message);
    }

    public PlayerException(String message, Throwable cause) { // [cite: 211]
        super(message, cause);
    }

    public PlayerException(Throwable cause) { // [cite: 211]
        super(cause);
    }
}