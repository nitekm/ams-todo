package ncode.dev.todoservice.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ncode.dev.todoservice.grpc.BasicTaskServiceGrpc.BasicTaskServiceImplBase;
import ncode.dev.todoservice.service.TaskMapper;
import ncode.dev.todoservice.service.TaskService;
import ncode.dev.todoservice.grpc.Task;
import ncode.dev.todoservice.grpc.TaskId;
import ncode.dev.todoservice.grpc.UpdateTaskRequest;
import ncode.dev.todoservice.openapi.task.model.TaskDTO;
import ncode.dev.todoservice.grpc.Empty;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TaskServiceGrpc extends BasicTaskServiceImplBase {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Override
    public void createTask(final Task request, final StreamObserver<Task> responseObserver) {
        log.info("Creating Task: {}", request);
        final TaskDTO taskDTO = taskService.createNewTask(taskMapper.mapTotaskDTO(request));

        log.info("Created Task:{}", taskDTO);
        responseObserver.onNext(Task.newBuilder(taskMapper.mapToTask(taskDTO)).build());
        responseObserver.onCompleted();

    }

    @Override
    public void deleteTask(final TaskId request, final StreamObserver<Empty> responseObserver) {
        log.info("Deleting task #{}", request.getId());

        taskService.deleteTask(request.getId());
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllTasks(final Empty request, final StreamObserver<Task> responseObserver) {
        log.info("Returning all Tasks");
        List<TaskDTO> taskDTOs = taskService.getAllTasks();

        taskDTOs.forEach(taskDTO -> responseObserver.onNext(taskMapper.mapToTask(taskDTO)));
        responseObserver.onCompleted();
    }

    @Override
    public void getOneTask(final TaskId request, final StreamObserver<Task> responseObserver) {
        //TODO: implement
    }

    @Override
    public void updateTask(final UpdateTaskRequest request, final StreamObserver<Task> responseObserver) {
        //TODO: implement
    }
}
