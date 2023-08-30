package peaksoft.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoTask.TaskResponse;
import peaksoft.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select new peaksoft.dto.dtoTask.TaskResponse(" +
            "t.id, " +
            "t.taskName, " +
            "t.taskText, " +
            "t.deadLine) from Task t")
    List<TaskResponse> getAllTasks();

    Optional<TaskResponse> getTaskById(Long id);

    @Query("select new peaksoft.dto.dtoTask.TaskResponse(" +
            "t.id, " +
            "t.taskName, " +
            "t.taskText, " +
            "t.deadLine) from Task t " +
            "join t.lesson c " +
            "where c.id = :lessonId")
    List<TaskResponse> findAllTaskByLessonId(Long lessonId);
}