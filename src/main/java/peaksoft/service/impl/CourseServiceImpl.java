package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.CompanyRepository;
import peaksoft.repasitory.CourseRepository;
import peaksoft.service.CourseService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    @Override
    public SimpleResponse saveCourse(Long companyId, CourseRequest courseRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new NotFoundException("Company with id: " + companyId +
                        " not founded"));
        Course course=new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(LocalDate.now());
        course.setDescription(courseRequest.getDescription());
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Course with id: %s successfully saved",course.getId())).build();
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        return courseRepository.getCourseById(id).orElseThrow(
                ()->new NotFoundException
                        ("Course with id: "+id+" not found"));
    }

    @Override
    public SimpleResponse updateCourse(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException
                        ("Course with id: " + id + " not found"));
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        courseRepository.save(course);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Course with id: %s successfully updated",course.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteCourse(Long id) {
        if(!courseRepository.existsById(id)){
            throw new NotFoundException("Company with id: "+id+" not found");
        }
        courseRepository.deleteById(id);

        return new SimpleResponse(
                HttpStatus.OK,
                "Course with id: "+id+" is deleted"
        );
    }

    @Override
    public List<CourseResponse> findAll(Long companyId) {
        return courseRepository.findCourseByCompanyId(companyId);
    }
}
