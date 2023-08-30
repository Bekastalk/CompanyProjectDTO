package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoLesson.LessonRequest;
import peaksoft.dto.dtoLesson.LessonResponse;
import peaksoft.dto.dtoTask.TaskRequest;
import peaksoft.dto.dtoTask.TaskResponse;

import java.util.List;

public interface LessonService {
    SimpleResponse saveLesson(Long courseId,LessonRequest lessonRequest);
    List<LessonResponse> getAllLesson();
    LessonResponse getLessonById(Long id);
    SimpleResponse updateLesson(Long id, LessonRequest LessonRequest);
    SimpleResponse deleteLesson(Long id);

    List<LessonResponse> findAll(Long groupId);
}
