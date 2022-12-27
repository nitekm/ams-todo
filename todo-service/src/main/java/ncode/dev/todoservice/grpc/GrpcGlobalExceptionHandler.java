package ncode.dev.todoservice.grpc;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.protobuf.ProtoUtils;
import ncode.dev.todoservice.exception.TaskNotFoundException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import ncode.dev.todoservice.grpc.Error;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcGlobalExceptionHandler {

    private static final Metadata.Key<Error> STATUS_ERROR =
            Metadata.Key.of("grpc-error-bin",
                    ProtoUtils.metadataMarshaller(Error.getDefaultInstance()));

    @GrpcExceptionHandler
    public StatusException handleTaskNotFound(TaskNotFoundException e) {
        Status status = Status.NOT_FOUND.withDescription("Task not found").withCause(e);

        Metadata metadata = new Metadata();
        metadata.put(STATUS_ERROR, Error.newBuilder()
                .setMessage("Task not found")
                .setCode(Error.Code.NOT_FOUND)
                .build());
        return status.asException(metadata);
    }
}
