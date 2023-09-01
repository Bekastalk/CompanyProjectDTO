package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoGroup.CountStud;
import peaksoft.dto.dtoGroup.GroupRequest;
import peaksoft.dto.dtoGroup.GroupResponse;
import peaksoft.repasitory.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupApi {
    private final GroupService groupService;

    @PostMapping("/{courseId}")
    SimpleResponse save(@PathVariable Long courseId,
                        @RequestBody GroupRequest groupRequest){
        return groupService.saveGroup(courseId, groupRequest);
    }

    @GetMapping
    List<GroupResponse> getAll(){
        return groupService.getAllGroup();
    }

    @GetMapping("/{groupId}/get")
    GroupResponse getById(@PathVariable Long groupId){
        return groupService.getGroupById(groupId);
    }

    @PutMapping("/{groupId}")
    SimpleResponse update(@PathVariable Long groupId,
                          @RequestBody GroupRequest groupRequest){
        return groupService.updateGroup(groupId, groupRequest);
    }

    @DeleteMapping("/{groupId}")
    SimpleResponse delete(@PathVariable Long groupId){
        return groupService.deleteGroup(groupId);
    }

    @GetMapping("/{courseId}")
    List<GroupResponse> findAllByCourseId(@PathVariable Long courseId){
        return groupService.findAllCourseId(courseId);
    }

    @GetMapping("/{id}/count")
    CountStud countStud(@PathVariable Long id){
        return groupService.countStud(id);
    }
}
