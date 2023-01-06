package ncode.dev.bffwife.client;

import ncode.dev.bffwife.model.ScheduleRequestDTO;
import ncode.dev.bffwife.model.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "todo-service")
public interface TaskClient {

    @GetMapping(value = "/tasks")
    List<TaskDTO> getAllTasks();

    @GetMapping(value = "/tasks/{id}")
    TaskDTO getTaskById(@PathVariable("id") String id);

    @PostMapping(value = "/tasks")
    TaskDTO createTask(@RequestBody TaskDTO taskDTO);

    @PutMapping(value = "/tasks/{id}")
    TaskDTO updateTask(@RequestBody TaskDTO taskDTO, @PathVariable("id") String id);

    @DeleteMapping(value = "/tasks/{id}")
    Void deleteTask(@PathVariable("id") String id);

    @PostMapping(value = "/schedule")
    TaskDTO scheduleTask(@RequestBody ScheduleRequestDTO scheduleRequestDTO);

    @PatchMapping(value = "/tasks/{id}")
    TaskDTO switchDone(@PathVariable("id") String id);
}
