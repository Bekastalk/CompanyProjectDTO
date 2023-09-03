package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoLesson.LessonRequest;
import peaksoft.dto.dtoLesson.LessonResponse;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{lessons}")
public class LessonApi {
    private final LessonService lessonService;

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/{courseId}")
    SimpleResponse saveLesson(@PathVariable Long courseId,
                        @RequestBody LessonRequest lessonRequest){
        return lessonService.saveLesson(courseId, lessonRequest);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping
    List<LessonResponse> getAll(){
        return lessonService.getAllLesson();
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/{lessonId}/get")
    LessonResponse getById(@PathVariable Long lessonId){
        return lessonService.getLessonById(lessonId);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PutMapping("/{lessonId}")
    SimpleResponse update(@PathVariable Long lessonId,
                          @RequestBody LessonRequest lessonRequest){
        return lessonService.updateLesson(lessonId, lessonRequest);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @DeleteMapping("/{lessonId}")
    SimpleResponse delete(@PathVariable Long lessonId){
        return lessonService.deleteLesson(lessonId);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/{courseId}")
    List<LessonResponse> findAllByCourseId(@PathVariable Long courseId){
        return lessonService.findAll(courseId);
    }

}
