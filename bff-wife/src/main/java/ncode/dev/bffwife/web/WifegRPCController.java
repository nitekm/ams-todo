package ncode.dev.bffwife.web;

import lombok.extern.slf4j.Slf4j;
import ncode.dev.bffwife.grpc.TaskGrpcService;
import ncode.dev.bffwife.model.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grpc")
@Slf4j
public class WifegRPCController {

    private final TaskGrpcService taskGrpcService;

    public WifegRPCController(final TaskGrpcService taskGrpcService) {
        this.taskGrpcService = taskGrpcService;
    }

    @GetMapping("/getAll")
    public void getAll() {
         taskGrpcService.getAllTasks()
                 .thenAccept(tasks -> log.info("Tasks list:\n {}", tasks));
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TaskDTO> getOneById(@PathVariable("id") String id) {
        return ResponseEntity.ok(taskGrpcService.getTaskById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskGrpcService.createTask(taskDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable("id") String id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskGrpcService.updateTask(id, taskDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        taskGrpcService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
