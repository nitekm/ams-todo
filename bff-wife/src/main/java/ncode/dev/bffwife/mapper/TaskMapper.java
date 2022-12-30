package ncode.dev.bffwife.mapper;

import ncode.dev.bffwife.model.PriorityEnum;
import ncode.dev.bffwife.model.TaskDTO;
import ncode.dev.bffwife.grpc.Priority;
import ncode.dev.bffwife.grpc.Task;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class TaskMapper {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public TaskDTO mapTotaskDTO(Task task) {
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

        return Task.newBuilder()
                .setName(taskDTO.getName())
                .setDescription(taskDTO.getDescription())
                .setDueDate(taskDTO.getDueDate().format(formatter))
                .setScheduled(taskDTO.getScheduled().format(formatter))
                .setPriority(mapToPriority(taskDTO.getPriority()))
                .setDone(taskDTO.isDone())
                .build();
    }

    private Priority mapToPriority(PriorityEnum priorityEnum) {
        switch (priorityEnum) {
            case P1:
                return Priority.P1;
            case P2:
                return Priority.P2;
            case P3:
                return Priority.P3;
            case P4:
                return Priority.P4;
            default:
                return null;
        }
    }

    private PriorityEnum mapToPriorityEnum(Priority priority) {
        switch (priority) {
            case P1:
                return PriorityEnum.P1;
            case P2:
                return PriorityEnum.P2;
            case P3:
                return PriorityEnum.P3;
            case P4:
                return PriorityEnum.P4;
            default:
                return null;
        }
    }
}
