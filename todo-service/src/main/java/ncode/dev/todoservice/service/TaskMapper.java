package ncode.dev.todoservice.service;

import ncode.dev.todoservice.grpc.Priority;
import ncode.dev.todoservice.grpc.Task;
import org.springframework.stereotype.Component;

import ncode.dev.todoservice.openapi.task.model.TaskDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Component
public class TaskMapper {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public TaskDTO mapTotaskDTO (Task task) {
        var taskDTO = new TaskDTO();

        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(LocalDate.parse(task.getDueDate(), formatter));
        taskDTO.setScheduled(LocalDate.parse(task.getScheduled(), formatter));
        taskDTO.setPriority(mapToPriorityEnum(task.getPriority()));
        taskDTO.setDone(task.getDone());

        return taskDTO;
    }

    public Task mapToTask(TaskDTO taskDTO) {

        var dueDate = Optional.of(taskDTO.getDueDate()).orElse(LocalDate.of(2030, 12, 31));
        var scheduled = Optional.ofNullable(taskDTO.getScheduled()).orElse(LocalDate.now());

        return Task.newBuilder()
                .setName(taskDTO.getName())
                .setDescription(taskDTO.getDescription())
                .setDueDate(dueDate.format(formatter))
                .setScheduled(scheduled.format(formatter))
                .setPriority(mapToPriority(taskDTO.getPriority()))
                .setDone(taskDTO.isDone())
                .build();
    }

    private Priority mapToPriority(TaskDTO.PriorityEnum priorityEnum) {
        switch (priorityEnum) {
            case P1: return Priority.P1;
            case P2: return Priority.P2;
            case P3: return Priority.P3;
            case P4: return Priority.P4;
            default: return null;
        }
    }

    private TaskDTO.PriorityEnum mapToPriorityEnum(Priority priority) {
        switch (priority) {
            case P1: return TaskDTO.PriorityEnum.P1;
            case P2: return TaskDTO.PriorityEnum.P2;
            case P3: return TaskDTO.PriorityEnum.P3;
            case P4: return TaskDTO.PriorityEnum.P4;
            default: return null;
        }
    }
}
