package ncode.dev.bffwife.grpc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ncode.dev.bffwife.mapper.TaskMapper;
import ncode.dev.bffwife.model.TaskDTO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskGrpcService {

    @Autowired
    private TaskMapper taskMapper;

    @GrpcClient("basicTask")
    private ncode.dev.bffwife.grpc.BasicTaskServiceGrpc.BasicTaskServiceBlockingStub taskBlockingStub;

    public CompletableFuture<List<TaskDTO>> getAllTasks() {

        return CompletableFuture.supplyAsync(() -> {
            log.info("Fetching task via gRPC");
            var tasks = taskBlockingStub.getAllTasks(ncode.dev.bffwife.grpc.Empty.newBuilder().build());

            var result = new ArrayList<TaskDTO>();
            tasks.forEachRemaining(task -> {
                log.info("Received task: {}", task);
                result.add(taskMapper.mapTotaskDTO(task));
            });
            return result;
        });
    }

    public TaskDTO getTaskById(String id) {
        log.info("Fetching task with id: {} via gRPC", id);
        var task = taskBlockingStub.getOneTask(
                ncode.dev.bffwife.grpc.TaskId
                        .newBuilder()
                        .setId(id)
                        .build());

        return taskMapper.mapTotaskDTO(task);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        log.info("Creating task via gRPC with data: {}", taskDTO);
        var task = taskMapper.mapToTask(taskDTO);
        var createdTask = taskBlockingStub.createTask(task);

        return taskMapper.mapTotaskDTO(createdTask);
    }

    public TaskDTO updateTask(String id, TaskDTO taskUpdateDTO) {
        log.info("Updating task via gRPC with id: {} with data: {}", id, taskUpdateDTO);
        var taskUpdate = taskMapper.mapToTask(taskUpdateDTO);
        var task = taskBlockingStub.updateTask(
                ncode.dev.bffwife.grpc.UpdateTaskRequest
                        .newBuilder()
                        .setId(id)
                        .setTask(taskUpdate)
                        .build());

        return taskMapper.mapTotaskDTO(task);
    }

    public void deleteTask(String id) {
        log.info("Deleting task with id: {}", id);

        taskBlockingStub.deleteTask(
                ncode.dev.bffwife.grpc.TaskId
                        .newBuilder()
                        .setId(id)
                        .build());
    }
}
