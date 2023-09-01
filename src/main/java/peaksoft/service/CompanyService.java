package peaksoft.service;


import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyRespoce;
import peaksoft.dto.GetCompanyInformation;
import peaksoft.dto.SimpleResponse;

import java.util.List;

public interface CompanyService {
    SimpleResponse saveCompany(CompanyRequest companyRequest);
    List<CompanyRespoce> getAllCompanies();
    CompanyRespoce getCompamyById(Long id);
    SimpleResponse updateCompany(Long id, CompanyRequest companyRequest);
    SimpleResponse deleteCompany(Long id);

    List<GetCompanyInformation> getCompanyInformation(Long id);

}
