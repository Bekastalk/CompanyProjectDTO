package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoInstructor.InstructorRequest;
import peaksoft.dto.dtoInstructor.InstructorResponse;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructors")

public class InstructorApi {

    private final InstructorService instructorService;

    @PostMapping
    SimpleResponse saveInstructor(@RequestBody InstructorRequest instructorRequest){
        return instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping
    List<InstructorResponse> getAllInstructors(){
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{instructorId}/get")
    InstructorResponse getById(@PathVariable Long instructorId){
        return instructorService.getInstructorById(instructorId);
    }

    @PutMapping("{instructorId}")
    SimpleResponse update(@PathVariable Long instructorId,
                              @RequestBody InstructorRequest instructorRequest){
        return instructorService.updateInstructor(instructorId, instructorRequest);
    }

    @DeleteMapping("/{instructorId}")
    SimpleResponse delete(@PathVariable Long instructorId){
        return instructorService.deleteInstructor(instructorId);
    }

    @GetMapping("/{companyId}")
    List<InstructorResponse> findAll(@PathVariable Long companyId){
        return instructorService.findAll(companyId);
    }
}
