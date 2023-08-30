package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.exeptions.InvalidEmailException;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.CompanyRepository;
import peaksoft.repasitory.StudentRepository;
import peaksoft.service.StudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;

    @Override
    public SimpleResponse saveStudent(StudentRequest studentRequest) {
        Student byEmail = studentRepository.findByEmail(studentRequest.getEmail());
        if(byEmail== null) {
            Student student = new Student();
            student.setFirstName(studentRequest.getFirstName());
            student.setLastName(studentRequest.getLastName());
            student.setPhoneNumber(studentRequest.getPhoneNumber());
            student.setEmail(studentRequest.getEmail());
            student.setStudyFormat(studentRequest.getStudyFormat());
            studentRepository.save(student);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(String.format("Student with id: %s successfully saved",student.getId()))
                    .build();
        }else  throw new InvalidEmailException(String.format("такой email: %s уже существует", studentRequest.getEmail()));


    }

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentRepository.getAllStudent();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepository.getStudentsById(id).orElseThrow(
                () -> new NotFoundException
                        ("Student with id: " + id + " not found"));
    }

    @Override
    public SimpleResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException
                        ("Student with id: " + id + " not found"));
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        studentRepository.save(student);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved",student.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new NotFoundException("Student with id: "+id+" not found");
        }
        studentRepository.deleteById(id);

        return new SimpleResponse(
                HttpStatus.OK,
                "Student with id: "+id+" is deleted"
        );
    }

    @Override
    public List<StudentResponse> findAll(Long groupId) {
        return studentRepository.findAllStudentsByGroupId(groupId);
    }
}
