package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoLesson.LessonRequest;
import peaksoft.dto.dtoLesson.LessonResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.CourseRepository;
import peaksoft.repasitory.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public SimpleResponse saveLesson(Long courseId, LessonRequest lessonRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException
                ("Course with id: " + courseId + " not found"));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        course.addLesson(lesson);
        lesson.setCourses(course);
        lessonRepository.save(lesson);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Lesson with id: %s successfully saved", lesson.getId()))
                .build();
    }

    @Override
    public List<LessonResponse> getAllLesson() {
        return lessonRepository.getAllLessons();
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        return lessonRepository.getLessonById(id).orElseThrow(
                () -> new NotFoundException
                        ("Lesson with id: " + id + " not found"));
    }

    @Override
    public SimpleResponse updateLesson(Long id, LessonRequest LessonRequest) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(
                () -> new NotFoundException
                        ("Lesson with id: " + id + " not found"));
        lesson.setLessonName(LessonRequest.getLessonName());
        lessonRepository.save(lesson);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Lesson with id: %s successfully saved", lesson.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteLesson(Long id) {
        if(!lessonRepository.existsById(id)){
            throw new NotFoundException("Lesson with id: "+id+" not found");
        }else {
            List<Course> courses = courseRepository.findAll();
            courses.forEach(course -> course.getLessons().removeIf(cor -> cor.getId().equals(id)));
            lessonRepository.deleteById(id);
        }
        return new SimpleResponse(
                HttpStatus.OK,
                "Lesson with id: " + id + " is deleted"
        );
    }

    @Override
    public List<LessonResponse> findAll(Long courseId) {
        return lessonRepository.findLessonByCourseId(courseId);
    }
}
