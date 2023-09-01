package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.dto.dtoGroup.CountStud;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    SimpleResponse saveStudent(StudentRequest studentRequest);
    List<StudentResponse> getAllStudent();
    StudentResponse getStudentById(Long id);
    SimpleResponse updateStudent(Long id, StudentRequest studentRequest);
    SimpleResponse deleteStudent(Long id);

    List<StudentResponse> findAll(Long groupId);

    SimpleResponse assign(Long studentId, Long groupId);

    List<StudentResponse> filterStudyFormat(StudyFormat studyFormat);
}
