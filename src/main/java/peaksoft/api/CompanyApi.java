package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CompanyRequest;
import peaksoft.dto.CompanyRespoce;
import peaksoft.dto.SimpleResponse;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyApi {
    private final CompanyService companyService;

    @PostMapping
    SimpleResponse saveComp(@RequestBody CompanyRequest companyRequest){
        return companyService.saveCompany(companyRequest);
    }

    @GetMapping
    List<CompanyRespoce> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{companyId}")
    CompanyRespoce getById(@PathVariable Long companyId){
        return companyService.getCompamyById(companyId);
    }

    @PutMapping("/{companyId}")
    SimpleResponse updateCompany(@PathVariable Long companyId,
                                 @RequestBody CompanyRequest companyRequest){
        return companyService.updateCompany(companyId, companyRequest);
    }

    @DeleteMapping("/{companyId}")
    SimpleResponse deleteCompany(@PathVariable Long companyId){
        return companyService.deleteCompany(companyId);
    }

}
