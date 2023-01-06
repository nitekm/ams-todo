package ncode.dev.bffwife.web;

import ncode.dev.bffwife.model.ScheduleRequestDTO;
import ncode.dev.bffwife.model.TaskDTO;
import ncode.dev.bffwife.client.TaskClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class WifeRestController {
    private final TaskClient taskClient;

    public WifeRestController(final TaskClient basicClient) {
        this.taskClient = basicClient;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TaskDTO>> getAll() {
        return ResponseEntity.ok(taskClient.getAllTasks());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TaskDTO> getOneById(@PathVariable("id") String id) {
        return ResponseEntity.ok(taskClient.getTaskById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskClient.createTask(taskDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable("id") String id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskClient.updateTask(taskDTO, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(taskClient.deleteTask(id));
    }

    @PostMapping("/schedule")
    public ResponseEntity<TaskDTO> scheduleTask(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return ResponseEntity.ok(taskClient.scheduleTask(scheduleRequestDTO));
    }

}
