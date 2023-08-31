package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoInstructor.InstructorCountStudent;
import peaksoft.dto.dtoInstructor.InstructorRequest;
import peaksoft.dto.dtoInstructor.InstructorResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.CompanyRepository;
import peaksoft.repasitory.CourseRepository;
import peaksoft.repasitory.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    @Override
    public SimpleResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor=new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Instructor with id: %s successfully saved",instructor.getId()))
                .build();
    }

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.getAllInstructors();
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        return instructorRepository.getInstructorById(id).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + id + " not found"));
    }

    @Override
    public SimpleResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + id + " not found"));
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Instructor with id: %s successfully saved",instructor.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteInstructor(Long id) {
        if(!instructorRepository.existsById(id)){
            throw new NotFoundException("Instructor with id: "+id+" not found");
        }else {
            List<Company> companies = companyRepository.findAll();
            List<Course> courses = courseRepository.findAll();

            companies.forEach(c -> c.getInstructors().removeIf(com -> com.getId().equals(id)));
            courses.forEach(course -> {
                if (course.getInstructor().getId().equals(id)) {
                    course.setInstructor(null); // Удаление инструктора из курса
                }
            });
            instructorRepository.deleteById(id);
        }
        return new SimpleResponse(
                HttpStatus.OK,
                "Instructor with id: "+id+" is deleted"
        );
    }

    @Override
    public List<InstructorResponse> findAll(Long companyId) {
        return instructorRepository.findInstructorByCompanyId(companyId);
    }

    @Override
    public SimpleResponse assign(Long instructorId, Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new NotFoundException
                        ("Company with id: " + companyId + " not found"));
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + instructorId + " not found"));
        instructor.getCompanies().add(company);
        company.getInstructors().add(instructor);
        companyRepository.save(company);
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Instructor with id: %s successfully assigned",instructor.getId()))
                .build();

    }

    @Override
    public SimpleResponse assignToCourse(Long instructorId, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException
                        ("Course with id: " + courseId + " not found"));
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + instructorId + " not found"));
        instructor.getCourses().add(course);
        course.setInstructor(instructor);

        courseRepository.save(course);
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Instructor with id: %s successfully assigned",instructor.getId()))
                .build();
    }

    @Override
    public InstructorCountStudent countStudent(Long id) {
        return instructorRepository.getInstructorCountStudents(id);
    }
}
