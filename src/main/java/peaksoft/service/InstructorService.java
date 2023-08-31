package peaksoft.service;

import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyRespoce;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoInstructor.InstructorCountStudent;
import peaksoft.dto.dtoInstructor.InstructorRequest;
import peaksoft.dto.dtoInstructor.InstructorResponse;

import java.util.List;

public interface InstructorService {
    SimpleResponse saveInstructor(InstructorRequest instructorRequest);
    List<InstructorResponse> getAllInstructors();
    InstructorResponse getInstructorById(Long id);
    SimpleResponse updateInstructor(Long id, InstructorRequest instructorRequest);
    SimpleResponse deleteInstructor(Long id);

    List<InstructorResponse> findAll(Long companyId);

    SimpleResponse assign(Long instructorId, Long companyId);

    SimpleResponse assignToCourse(Long instructorId, Long courseId);

    InstructorCountStudent countStudent(Long id);
}
