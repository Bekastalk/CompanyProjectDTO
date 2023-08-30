package peaksoft.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CompanyRespoce {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private LocalDate createdDate;
    private LocalDate graduationDate;

    public CompanyRespoce(Long id, String name, String country, String address, String phoneNumber, LocalDate createdDate, LocalDate graduationDate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.graduationDate = graduationDate;
    }
}
