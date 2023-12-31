package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentApi {
    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping()
    SimpleResponse save(
                        @RequestBody StudentRequest studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping
    List<StudentResponse> getAll(){
        return studentService.getAllStudent();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/{studentId}/get")
    StudentResponse getById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/{studentId}")
    SimpleResponse update(@PathVariable Long studentId,
                          @RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentId, studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/{studentId}")
    SimpleResponse delete(@PathVariable Long studentId){
        return studentService.deleteStudent(studentId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/{groupId}")
    List<StudentResponse> findAll(@PathVariable Long groupId){
        return studentService.findAll(groupId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("/assign")
    public SimpleResponse assignInstructorToCompany(
            @RequestParam Long studentId,
            @RequestParam Long groupId){
        return studentService.assign(studentId,groupId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/studyFormat")
    public List<StudentResponse> sortStudyFormat(@RequestParam StudyFormat studyFormat){
        return studentService.filterStudyFormat(studyFormat);
    }
}
