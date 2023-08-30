package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.dto.dtoTask.TaskRequest;
import peaksoft.dto.dtoTask.TaskResponse;

import java.util.List;

public interface TaskService {
    SimpleResponse saveTask(Long lessonId,TaskRequest taskRequest);
    List<TaskResponse> getAllTask();
    TaskResponse getTaskById(Long id);
    SimpleResponse updateTask(Long id, TaskRequest taskRequest);
    SimpleResponse deleteTask(Long id);

    List<TaskResponse> findAll(Long groupId);
}
