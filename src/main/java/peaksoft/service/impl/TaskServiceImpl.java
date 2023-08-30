package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoTask.TaskRequest;
import peaksoft.dto.dtoTask.TaskResponse;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.LessonRepository;
import peaksoft.repasitory.TaskRepository;
import peaksoft.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public SimpleResponse saveTask(Long lessonId,TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + lessonId + " not found"));
        Task task=new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadLine(taskRequest.getDeadLine());
        lesson.addTask(task);
        taskRepository.save(task);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved",task.getId()))
                .build();
    }

    @Override
    public List<TaskResponse> getAllTask() {
        return taskRepository.getAllTasks();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return taskRepository.getTaskById(id).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + id + " not found"));
    }

    @Override
    public SimpleResponse updateTask(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + id + " not found"));
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadLine(taskRequest.getDeadLine());
        taskRepository.save(task);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved",task.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteTask(Long id) {
        if(!taskRepository.existsById(id)){
            throw new NotFoundException("Task with id: "+id+" not found");
        }
        taskRepository.deleteById(id);

        return new SimpleResponse(
                HttpStatus.OK,
                "Task with id: "+id+" is deleted"
        );
    }

    @Override
    public List<TaskResponse> findAll(Long lessonId) {
        return taskRepository.findAllTaskByLessonId(lessonId);
    }
}
