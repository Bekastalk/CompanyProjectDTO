package peaksoft.dto.dtoStudent;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.StudyFormat;
@Setter
@Getter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
}
