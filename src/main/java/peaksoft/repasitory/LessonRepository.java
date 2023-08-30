package peaksoft.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoLesson.LessonResponse;
import peaksoft.entity.Lesson;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select new peaksoft.dto.dtoLesson.LessonResponse(" +
            "l.id," +
            "l.lessonName) from Lesson l")
    List<LessonResponse> getAllLessons();

    Optional<LessonResponse> getLessonById(Long id);

    @Query("select new peaksoft.dto.dtoLesson.LessonResponse(" +
            "l.id," +
            "l.lessonName) from Lesson l " +
            "join l.courses c " +
            "where c.id = :courseId")
    List<LessonResponse> findLessonByCourseId(Long courseId);
}