package peaksoft.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CompanyRespoce;
import peaksoft.entity.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select new peaksoft.dto.CompanyRespoce(c.id," +
            "c.name," +
            "c.country," +
            "c.address," +
            "c.phoneNumber," +
            "c.createdDate," +
            "c.graduationDate) from Company c")
    List<CompanyRespoce> getAllCompanies();

    Optional<CompanyRespoce> getCompanyById(Long id);

    Company findByName(String name);
}