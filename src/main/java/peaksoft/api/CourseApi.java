package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseApi {
    private final CourseService courseService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{companyId}")
    SimpleResponse saveCourse(@PathVariable Long companyId,
                              @RequestBody CourseRequest courses){
        return courseService.saveCourse(companyId, courses);
    }

    @PreAuthorize("hasAnyAAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping
    List<CourseResponse> getAll(){
        return courseService.getAllCourses();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{courseId}/get")
    CourseResponse getById(@PathVariable Long courseId){
        return courseService.getCourseById(courseId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{courseId}")
    SimpleResponse update(@PathVariable Long courseId, @RequestBody CourseRequest courseRequest){
        return courseService.updateCourse(courseId, courseRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{courseId}")
    SimpleResponse delete(@PathVariable Long courseId){
        return courseService.deleteCourse(courseId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{companyId}")
    List<CourseResponse> findAllCompanyId(@PathVariable Long companyId){
        return courseService.findAll(companyId);
    }

}

