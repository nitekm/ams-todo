package ncode.dev.todoservice.api;

import lombok.RequiredArgsConstructor;
import ncode.dev.todoservice.openapi.task.model.TaskDTO;
import ncode.dev.todoservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskBasicApiDelegateImpl implements ncode.dev.todoservice.openapi.task.TaskBasicApiDelegate {

    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskDTO> createNewTask(final TaskDTO body) {
        return ResponseEntity.ok(taskService.createNewTask(body));
    }

    @Override
    public ResponseEntity<Void> deleteTask(final String id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getAllTasks(final String name, final String description) {
        return ResponseEntity.ok(taskService.getAllTasks(name, description));
    }

    @Override
    public ResponseEntity<TaskDTO> getOneTask(final String id) {
        return ResponseEntity.ok(taskService.getOneTask(id));
    }

    @Override
    public ResponseEntity<TaskDTO> updateTask(final String id, final TaskDTO body) {
        return ResponseEntity.ok(taskService.updateTask(id, body));
    }
}
