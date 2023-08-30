package peaksoft.dto.dtoStudent;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.StudyFormat;
@Setter
@Getter

public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;

    public StudentResponse(Long id, String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}
