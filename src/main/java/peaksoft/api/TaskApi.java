package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/{lessonId}/save")
    SimpleResponse save(@PathVariable Long lessonId,
                        @RequestBody TaskRequest taskRequest){
        return taskService.saveTask(lessonId, taskRequest);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/task")
    List<TaskResponse> getAll(){
        return taskService.getAllTask();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{taskId}/taskById")
    TaskResponse getById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/{taskId}/task")
    SimpleResponse update(@PathVariable Long taskId,
                          @RequestBody TaskRequest taskRequest){
        return taskService.updateTask(taskId, taskRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR', 'STUDENT')")
    @DeleteMapping("/{taskId}/task")
    SimpleResponse delete(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{lessonId}/task")
    List<TaskResponse> findAll(@PathVariable Long lessonId){
        return taskService.findAll(lessonId);
    }

}
