package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoTask.TaskRequest;
import peaksoft.dto.dtoTask.TaskResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RequestMapping("/{tasks}")
@RequiredArgsConstructor
@RestController
public class TaskApi {
    private final TaskService taskService;

    @PostMapping("/{lessonId}")
    SimpleResponse save(@PathVariable Long lessonId,
                        @RequestBody TaskRequest taskRequest){
        return taskService.saveTask(lessonId, taskRequest);
    }

    @GetMapping
    List<TaskResponse> getAll(){
        return taskService.getAllTask();
    }

    @GetMapping("/{taskId}/get")
    TaskResponse getById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    SimpleResponse update(@PathVariable Long taskId,
                          @RequestBody TaskRequest taskRequest){
        return taskService.updateTask(taskId, taskRequest);
    }

    @DeleteMapping("/{taskId}")
    SimpleResponse delete(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }

    @GetMapping("/{lessonId}")
    List<TaskResponse> findAll(@PathVariable Long lessonId){
        return taskService.findAll(lessonId);
    }

}
