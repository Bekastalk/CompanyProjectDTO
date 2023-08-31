package peaksoft.dto.dtoInstructor;

public class InstructorCountStudent {

    private Long id;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private  String groupName;
    private int studentCount;

    public InstructorCountStudent(Long id, String firstName, String lastName, String phoneNumber, String specialization, String groupName, int studentCount) {
        this.id = id;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.groupName = groupName;
        this.studentCount = studentCount;
    }
}
