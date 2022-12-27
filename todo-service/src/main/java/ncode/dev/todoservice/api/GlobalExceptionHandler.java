package ncode.dev.todoservice.api;

import ncode.dev.todoservice.exception.SaveTaskException;
import ncode.dev.todoservice.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ncode.dev.todoservice.openapi.task.model.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleTaskNotFound(TaskNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO()
                        .message("Task does not exists")
                        .code(ErrorDTO.CodeEnum.NOT_FOUND));
    }

    @ExceptionHandler(SaveTaskException.class)
    public ResponseEntity<ErrorDTO> handleSaveTaskError(SaveTaskException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO()
                        .message("Error occured while saving task")
                        .code(ErrorDTO.CodeEnum.SAVE_ERROR));
    }
}
