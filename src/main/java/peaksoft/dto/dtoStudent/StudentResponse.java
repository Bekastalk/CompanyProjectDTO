package peaksoft.dto.dtoStudent;

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
    private Boolean block;

    public StudentResponse(Long id, String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat, Boolean block) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
        this.block=block;
    }
}
