package peaksoft.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new peaksoft.dto.dtoStudent.StudentResponse(s.id, " +
            "s.firstName, " +
            "s.lastName, " +
            "s.phoneNumber," +
            "s.email," +
            "s.studyFormat, " +
            "s.block) from Student s")
    List<StudentResponse> getAllStudent();


    Optional<StudentResponse> getStudentsById(Long id);

    @Query("select new peaksoft.dto.dtoStudent.StudentResponse(s.id, " +
            "s.firstName, " +
            "s.lastName, " +
            "s.phoneNumber," +
            "s.email," +
            "s.studyFormat," +
            "s.block) from Student s " +
            "join s.group c " +
            "where c.id = :groupId")
    List<StudentResponse> findAllStudentsByGroupId(Long groupId);

    Student findByEmail(String email);

    @Query("select new peaksoft.dto.dtoStudent.StudentResponse(s.id," +
            "s.firstName," +
            "s.lastName," +
            "s.phoneNumber," +
            "s.email," +
            "s.studyFormat," +
            "s.block)" +
            "from Student s where s.studyFormat = :studyFormat")
    List<StudentResponse> filterStudyFormat(StudyFormat studyFormat);
}