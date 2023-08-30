package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyRespoce;
import peaksoft.dto.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repasitory.CompanyRepository;
import peaksoft.service.CompanyService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    @Override
    public SimpleResponse saveCompany(CompanyRequest companyRequest) {
        Company company=new Company();
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
                .message(String.format("Company with id: %s successfully saved", company.getId()))
                        .build();
    }

    @Override
    public SimpleResponse deleteCompany(Long id) {
        if(!companyRepository.existsById(id)){
            throw new NotFoundException("Company with id: "+id+" not found");
        }
        companyRepository.deleteById(id);

        return new SimpleResponse(
                HttpStatus.OK,
                "Company with id: "+id+" is deleted"
        );
    }

}
