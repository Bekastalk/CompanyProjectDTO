package peaksoft.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.entity.Course;

import java.util.List;
import java.util.Optional;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select new peaksoft.dto.dtoCourse.CourseResponse(c.id," +
            "c.courseName," +
            " c.dateOfStart," +
            " c.description) from Course c order by c.dateOfStart")
    List<CourseResponse> getAllCourses();

    Optional<CourseResponse> getCourseById(Long id);

    List<CourseResponse> findCourseByCompanyId(Long companyId);
}