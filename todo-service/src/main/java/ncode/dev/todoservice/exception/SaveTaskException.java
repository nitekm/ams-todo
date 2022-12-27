package ncode.dev.todoservice.exception;

public class SaveTaskException extends RuntimeException {
    public SaveTaskException() {}

    public SaveTaskException(final String message) {
        super(message);
    }
}
