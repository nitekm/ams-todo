package ncode.dev.bffwife.web;

import ncode.dev.bffwife.model.TaskDTO;
import ncode.dev.bffwife.client.TaskBasicClient;
import ncode.dev.bffwife.client.TaskOperationClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class WifeRestController {
    private final TaskBasicClient basicClient;
    private final TaskOperationClient operationClient;

    public WifeRestController(final TaskBasicClient basicClient, final TaskOperationClient operationClient) {
        this.basicClient = basicClient;
        this.operationClient = operationClient;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TaskDTO>> getAll() {
        return ResponseEntity.ok(basicClient.getAllTasks());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<TaskDTO> getOneById(@PathVariable("id") String id) {
        return ResponseEntity.ok(basicClient.getTaskById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(basicClient.createTask(taskDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable("id") String id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(basicClient.updateTask(taskDTO, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(basicClient.deleteTask(id));
    }

}
