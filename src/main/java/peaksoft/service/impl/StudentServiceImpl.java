package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.exeptions.InvalidEmailException;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.GroupRepository;
import peaksoft.repasitory.StudentRepository;
import peaksoft.service.StudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

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
        }else {
            List<Group> groups = groupRepository.findAll();
            groups.forEach(group -> group.getStudents().removeIf(gr -> gr.getId().equals(id)));
            studentRepository.deleteById(id);
        }
        return new SimpleResponse(
                HttpStatus.OK,
                "Student with id: "+id+" is deleted"
        );
    }

    @Override
    public List<StudentResponse> findAll(Long groupId) {
        return studentRepository.findAllStudentsByGroupId(groupId);
    }

    @Override
    public SimpleResponse assign(Long studentId, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new NotFoundException
                        ("Group with id: " + groupId + " not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException
                        ("Student with id: " + studentId + " not found"));
        group.getStudents().add(student);
        student.setGroup(group);
        studentRepository.save(student);
        groupRepository.save(group);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully assigned",student.getId()))
                .build();
    }
}
