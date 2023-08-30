package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoGroup.GroupRequest;
import peaksoft.dto.dtoGroup.GroupResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.CourseRepository;
import peaksoft.repasitory.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;


    @Override
    public SimpleResponse saveGroup(Long courseId, GroupRequest groupRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NotFoundException("Course with id: " + courseId +
                        " not founded"));
        Group group=new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        course.addGroup(group);
        group.addCourse(course);
        groupRepository.save(group);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Group with id %s suсcessfully saved", group.getId()))
                .build();
    }

    @Override
    public List<GroupResponse> getAllGroup() {
        return groupRepository.getAllGroups();
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        return groupRepository.getGroupById(id).orElseThrow(
                ()->new NotFoundException
                        ("Group with id: "+id+" not found"));
    }

    @Override
    public SimpleResponse updateGroup(Long id, GroupRequest groupRequest) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException
                        ("Group with id: " + id + " not found"));
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        groupRepository.save(group);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Group with id %s successfully  updated", group.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteGroup(Long id) {
        if(!groupRepository.existsById(id)){
            throw new NotFoundException("Company with id: "+id+" not found");
        }
        groupRepository.deleteById(id);

        return new SimpleResponse(
                HttpStatus.OK,
                "Group with id: "+id+" is deleted"
        );
    }

    @Override
    public List<GroupResponse> findAllCourseId(Long courseId) {
        return groupRepository.findGroupByCourseId(courseId);
    }
}
