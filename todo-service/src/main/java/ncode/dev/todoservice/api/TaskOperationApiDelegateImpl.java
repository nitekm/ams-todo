package ncode.dev.todoservice.api;

import lombok.RequiredArgsConstructor;
import ncode.dev.todoservice.service.TaskService;
import ncode.dev.todoservice.openapi.task.model.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskOperationApiDelegateImpl implements ncode.dev.todoservice.openapi.task.TaskOperationApiDelegate {
    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskDTO> scheduleTask(final String id, final TaskDTO body) {
        return ResponseEntity.ok(taskService.scheduleTask(id, body));
    }

    @Override
    public ResponseEntity<TaskDTO> switchDone(final String id) {
        return ResponseEntity.ok(taskService.switchDone(id));
    }
}
