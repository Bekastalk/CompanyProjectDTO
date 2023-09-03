package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyRespoce;
import peaksoft.dto.GetCompanyInformation;
import peaksoft.dto.SimpleResponse;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyApi {
    private final CompanyService companyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    SimpleResponse saveComp(@RequestBody CompanyRequest companyRequest){
        return companyService.saveCompany(companyRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping
    List<CompanyRespoce> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{companyId}")
    CompanyRespoce getById(@PathVariable Long companyId){
        return companyService.getCompamyById(companyId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{companyId}")
    SimpleResponse updateCompany(@PathVariable Long companyId,
                                 @RequestBody CompanyRequest companyRequest){
        return companyService.updateCompany(companyId, companyRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{companyId}")
    SimpleResponse deleteCompany(@PathVariable Long companyId){
        return companyService.deleteCompany(companyId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{companyId}/info")
    List<GetCompanyInformation> getCompanyInformation(@PathVariable Long companyId){
        return companyService.getCompanyInformation(companyId);
    }

}
