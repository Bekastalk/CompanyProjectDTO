package peaksoft.dto.dtoInstructor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;

    public InstructorResponse(Long id, String firstName, String lastName, String phoneNumber, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

}
