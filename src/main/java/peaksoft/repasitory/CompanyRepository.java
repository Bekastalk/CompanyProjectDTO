package peaksoft.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CompanyRespoce;
import peaksoft.dto.GetCompanyInformation;
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

    @Query("SELECT NEW peaksoft.dto.GetCompanyInformation(c.id," +
            " c.name," +
            " c.address," +
            " c.phoneNumber," +
            " c.country," +
            " co.courseName," +
            " gr.groupName," +
            " i.firstName," +
            " CAST(COUNT(s.id) AS int)) " +
            "FROM Company c JOIN c.courses co " +
            "JOIN co.groups gr " +
            "JOIN gr.students s join c.instructors i " +
            "WHERE c.id = :id " +
            "GROUP BY c.id, c.name, c.address, " +
            "c.phoneNumber, c.country,co.courseName,gr.groupName,i.firstName ")
    List<GetCompanyInformation> getCompanyInformation(@Param("id")Long id);
}