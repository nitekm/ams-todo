package ncode.dev.todoservice.api;

import lombok.RequiredArgsConstructor;
import ncode.dev.todoservice.service.TaskService;
import ncode.dev.todoservice.openapi.task.model.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ncode.dev.todoservice.openapi.task.model.ScheduleRequestDTO;

@Component
@RequiredArgsConstructor
public class TaskOperationApiDelegateImpl implements ncode.dev.todoservice.openapi.task.TaskOperationApiDelegate {
    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskDTO> scheduleTask(final ScheduleRequestDTO scheduleRequestDTO) {
        return ResponseEntity.ok(taskService.scheduleTask(scheduleRequestDTO));
    }

    @Override
    public ResponseEntity<TaskDTO> switchDone(final String id) {
        return ResponseEntity.ok(taskService.switchDone(id));
    }
}
