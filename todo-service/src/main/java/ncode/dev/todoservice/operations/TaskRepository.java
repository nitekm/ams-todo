package ncode.dev.todoservice.operations;

import ncode.dev.todoservice.openapi.task.model.TaskDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class TaskRepository {

    public ncode.dev.todoservice.openapi.task.model.TaskDTO getTask(String id) {
        return new TaskDTO()
                .name("TaskToSchedue")
                .description("This task will be scheduled")
                .done(false)
                .dueDate(LocalDate.of(2024, 01, 22))
                .priority(TaskDTO.PriorityEnum.P3);
    }
}
