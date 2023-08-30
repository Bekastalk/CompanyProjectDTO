package peaksoft.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

public class CompanyRequest {
    @Column(unique = true)
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private LocalDate graduationDate;
}
