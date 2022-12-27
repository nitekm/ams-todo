package ncode.dev.todoservice.service;

import lombok.extern.slf4j.Slf4j;
import ncode.dev.todoservice.exception.TaskNotFoundException;
import ncode.dev.todoservice.openapi.task.model.TaskDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class TaskService {

    private static final String ERROR_ID = "101";

    public TaskDTO createNewTask(final TaskDTO body) {
        log.info("Creating new task: {}", body);
        return body;
    }


    public void deleteTask(final String id) {
        if (id.equals(ERROR_ID)) {
            log.error("Invalid id provided for method deleteTask");
            throw new TaskNotFoundException();
        }
        log.info("Deleting task with id: {}", id);
    }

    public List<TaskDTO> getAllTasks(final String name, final String description) {
        log.info("Returning all tasks");
        var tasks = Arrays.asList(
                new TaskDTO()
                        .name("Task 1")
                        .description("Task 1 description")
                        .dueDate(LocalDate.of(2024, 12, 12))
                        .priority(TaskDTO.PriorityEnum.P3)
                        .done(false),
                new TaskDTO()
                        .name("Task 2")
                        .description("Task 2 description")
                        .dueDate(LocalDate.of(2022, 10, 01))
                        .priority(TaskDTO.PriorityEnum.P2)
                        .done(false),
                new TaskDTO()
                        .name("Task 3")
                        .description("Task 3 description")
                        .dueDate(LocalDate.of(2022, 12, 11))
                        .priority(TaskDTO.PriorityEnum.P4)
                        .done(false),
                new TaskDTO()
                        .name("Task 4")
                        .description("Task 4 description")
                        .dueDate(LocalDate.of(2023, 01, 01))
                        .priority(TaskDTO.PriorityEnum.P2)
                        .done(false),
                new TaskDTO()
                        .name("Task 5")
                        .description("Task 5 description")
                        .dueDate(LocalDate.of(2023, 03, 02))
                        .priority(TaskDTO.PriorityEnum.P1)
                        .done(false)
        );
        return tasks;
    }

    public TaskDTO getOneTask(final String id) {
        if (id.equals(ERROR_ID)) {
            log.error("Invalid id provided for method getOneTask");
            throw new TaskNotFoundException();
        }
        log.info("Returning task with id: {}", id);
        return mockCreateTask();
    }

    public TaskDTO updateTask(final String id, final TaskDTO body) {
        if (id.equals(ERROR_ID)) {
            log.error("Invalid id provided for method switchDone");
            throw new TaskNotFoundException();
        }
        log.info("Updating task with id: {}, body: {}", id, body);
        return body;
    }

    public TaskDTO scheduleTask(final String id, final TaskDTO body) {
        return null;
    }

    public TaskDTO switchDone(final String id) {
        if (id.equals(ERROR_ID)) {
            log.error("Invalid id provided for method switchDone");
            throw new TaskNotFoundException();
        }
        log.info("Request for changing done received for Task id: {} ", id);
        return mockCreateTask();
    }

    private TaskDTO mockCreateTask() {
        return new TaskDTO()
                .name("Task 1")
                .description("Mock task #1 descriptive")
                .priority(TaskDTO.PriorityEnum.P2)
                .dueDate(LocalDate.of(2023, 01, 01))
                .done(true);
    }
}
