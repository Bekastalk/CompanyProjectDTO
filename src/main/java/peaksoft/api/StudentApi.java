package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentApi {
    private final StudentService studentService;

    @PostMapping()
    SimpleResponse save(
                        @RequestBody StudentRequest studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping
    List<StudentResponse> getAll(){
        return studentService.getAllStudent();
    }

    @GetMapping("/{studentId}/get")
    StudentResponse getById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    SimpleResponse update(@PathVariable Long studentId,
                          @RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentId, studentRequest);
    }

    @DeleteMapping("/{studentId}")
    SimpleResponse delete(@PathVariable Long studentId){
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/{groupId}")
    List<StudentResponse> findAll(@PathVariable Long groupId){
        return studentService.findAll(groupId);
    }

}
