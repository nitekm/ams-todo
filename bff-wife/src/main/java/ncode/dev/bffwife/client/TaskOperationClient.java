package ncode.dev.bffwife.client;

import ncode.dev.bffwife.model.TaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "taskOperation", url = "")
public interface TaskOperationClient {

    @PostMapping(value = "/tasks/{id}")
    TaskDTO scheduleTask(@RequestBody TaskDTO taskDTO, @PathVariable("id") String id);

    @PatchMapping(value = "/tasks/{id}")
    TaskDTO switchDone(@PathVariable("id") String id);

}
