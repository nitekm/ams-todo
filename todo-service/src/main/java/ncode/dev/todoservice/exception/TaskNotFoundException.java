package ncode.dev.todoservice.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {}

    public TaskNotFoundException(final String message) {
        super(message);
    }
}
