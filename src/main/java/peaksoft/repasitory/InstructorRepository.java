package peaksoft.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoInstructor.InstructorResponse;
import peaksoft.entity.Instructor;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query("select new peaksoft.dto.dtoInstructor.InstructorResponse(i.id," +
            " i.firstName, " +
            "i.lastName, " +
            "i.phoneNumber," +
            "i.specialization) from Instructor i")
    List<InstructorResponse> getAllInstructors();

    Optional<InstructorResponse> getInstructorById(Long id);


    @Query("select new peaksoft.dto.dtoInstructor.InstructorResponse(i.id," +
            " i.firstName, " +
            "i.lastName, " +
            "i.phoneNumber," +
            "i.specialization) from Instructor i " +
            "join i.companies c " +
            "where c.id = :companyId")
    List<InstructorResponse> findInstructorByCompanyId(Long companyId);
}