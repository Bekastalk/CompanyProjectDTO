package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoGroup.GroupRequest;
import peaksoft.dto.dtoGroup.GroupResponse;

import java.util.List;

public interface GroupService {
    SimpleResponse saveGroup(Long courseId, GroupRequest groupRequest);
    List<GroupResponse> getAllGroup();
    GroupResponse getGroupById(Long id);
    SimpleResponse updateGroup(Long id, GroupRequest groupRequest);
    SimpleResponse deleteGroup(Long id);

    List<GroupResponse> findAllCourseId(Long courseId);

}
