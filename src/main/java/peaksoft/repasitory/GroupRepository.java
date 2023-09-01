package peaksoft.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoGroup.CountStud;
import peaksoft.dto.dtoGroup.GroupResponse;
import peaksoft.entity.Group;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select new peaksoft.dto.dtoGroup.GroupResponse(g.id," +
            "g.groupName," +
            "g.imageLink, " +
            "g.description) from Group g")
    List<GroupResponse> getAllGroups();


    Optional<GroupResponse> getGroupById(Long id);



    @Query("select new peaksoft.dto.dtoGroup.GroupResponse(g.id," +
            "g.groupName," +
            "g.imageLink, " +
            "g.description) from Group g " +
            "join g.courses c " +
            "where c.id = :courseId ")
    List<GroupResponse> findGroupByCourseId(Long courseId);

    @Query("select new peaksoft.dto.dtoGroup.CountStud(cast(COUNT(s.id) as int ) ) from Group g " +
            "join g.students s " +
            "where g.id=:id")
    CountStud countStud(Long id);
}