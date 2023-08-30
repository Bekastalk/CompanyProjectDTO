package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoInstructor.InstructorRequest;
import peaksoft.dto.dtoInstructor.InstructorResponse;
import peaksoft.entity.Instructor;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Override
    public SimpleResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor=new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Instructor with id: %s successfully saved",instructor.getId()))
                .build();
    }

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.getAllInstructors();
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        return instructorRepository.getInstructorById(id).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + id + " not found"));
    }

    @Override
    public SimpleResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new NotFoundException
                        ("Instructor with id: " + id + " not found"));
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Instructor with id: %s successfully saved",instructor.getId()))
                .build();
    }

    @Override
    public SimpleResponse deleteInstructor(Long id) {
        if(!instructorRepository.existsById(id)){
            throw new NotFoundException("Company with id: "+id+" not found");
        }
        instructorRepository.deleteById(id);

        return new SimpleResponse(
                HttpStatus.OK,
                "Instructor with id: "+id+" is deleted"
        );
    }

    @Override
    public List<InstructorResponse> findAll(Long companyId) {
        return instructorRepository.findInstructorByCompanyId(companyId);
    }
}
