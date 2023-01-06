package ncode.dev.todoservice.operations;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import ncode.dev.todoservice.openapi.task.model.ScheduleRequestDTO;
import ncode.dev.todoservice.openapi.task.model.TaskDTO;

import java.io.Serializable;
import java.time.LocalDate;

import static ncode.dev.todoservice.config.Bindings.TASK_SCHEDULE_OUT;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskScheduleService {

    private final StreamBridge streamBridge;
    private final TaskRepository taskRepository;

    public TaskDTO scheduleTask(ScheduleRequestDTO scheduleRequestDTO) {
        log.info("Sending task to scheduler");

        TaskDTO taskDTO = taskRepository.getTask(scheduleRequestDTO.getId());
        taskDTO.setScheduled(scheduleRequestDTO.getScheduleDate());

        final ScheduleCommand scheduleCommand = ScheduleCommand.builder()
                .id(scheduleRequestDTO.getId())
                .taskName(taskDTO.getName())
                .description(taskDTO.getDescription())
                .done(taskDTO.isDone())
                .priority(taskDTO.getPriority())
                .dueDate(taskDTO.getDueDate())
                .scheduledDate(scheduleRequestDTO.getScheduleDate())
                .build();

        streamBridge.send(TASK_SCHEDULE_OUT, scheduleCommand);
        log.info("Task sent to scheduler");

        return taskDTO;
    }

    @Builder
    @Getter
    public static class ScheduleCommand {
        private String id;
        private String taskName;
        private String description;
        private Boolean done;
        private LocalDate dueDate;
        private LocalDate scheduledDate;
        private TaskDTO.PriorityEnum priority;
    }
}
