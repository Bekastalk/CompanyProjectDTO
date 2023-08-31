package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyRespoce;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Instructor;
import peaksoft.exeptions.InvalidEmailException;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.CompanyRepository;
import peaksoft.repasitory.InstructorRepository;
import peaksoft.service.CompanyService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final InstructorRepository instructorRepository;
    @Override
    public SimpleResponse saveCompany(CompanyRequest companyRequest) {
        Company byName = companyRepository.findByName(companyRequest.getName());
        if(byName==null) {
            Company company = new Company();
            company.setName(companyRequest.getName());
            company.setCountry(companyRequest.getCountry());
            company.setAddress(companyRequest.getAddress());
            company.setPhoneNumber(companyRequest.getPhoneNumber());
            company.setCreatedDate(LocalDate.now());
            company.setGraduationDate(companyRequest.getGraduationDate());
            companyRepository.save(company);
            return new SimpleResponse(
                    HttpStatus.OK,
                    String.format("Company with id: %s successfully saved", company.getId())
            );
        }else throw new InvalidEmailException(String.format("такое название : %s уже существует", companyRequest.getName()));
    }

    @Override
    public List<CompanyRespoce> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    @Override
    public CompanyRespoce getCompamyById(Long id) {
        return companyRepository.getCompanyById(id).orElseThrow(
                ()->new NotFoundException
                ("Company with id: "+id+" not found"));
    }

    @Override
    public SimpleResponse updateCompany(Long id, CompanyRequest companyRequest) {
        Company company=companyRepository.findById(id).orElseThrow(
                ()->new NotFoundException
                        ("Company with id: "+id+" not found"));
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        company.setGraduationDate(companyRequest.getGraduationDate());
        companyRepository.save(company);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Company with id: %s successfully updated", company.getId()))
                        .build();
    }

    @Override
    public SimpleResponse deleteCompany(Long id) {
        if(!companyRepository.existsById(id)){
            throw new NotFoundException("Company with id: "+id+" not found");
        }
        else {
            List<Instructor> instructors = instructorRepository.findAll();
            instructors.forEach(instructor -> instructor.getCompanies().removeIf(in->in.getId().equals(id)));
            companyRepository.deleteById(id);
        }
        return new SimpleResponse(
                HttpStatus.OK,
                "Company with id: "+id+" is deleted"
        );
    }

}
