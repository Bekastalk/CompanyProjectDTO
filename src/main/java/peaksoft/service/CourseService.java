package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;

import java.util.List;

public interface CourseService {
    SimpleResponse saveCourse(Long companyId,CourseRequest courseRequest);
    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(Long id);
    SimpleResponse updateCourse(Long id, CourseRequest courseRequest);
    SimpleResponse deleteCourse(Long id);

    List<CourseResponse> findAll(Long companyId);

}
