package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCompanyInformation {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private String courseName;
    private String groupName;
    private String instructorName;
    private int studentCount;

    public GetCompanyInformation(Long id, String name, String country, String address, String phoneNumber, String courseName, String groupName, String instructorName, int studentCount) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.courseName = courseName;
        this.groupName = groupName;
        this.instructorName = instructorName;
        this.studentCount = studentCount;
    }
}
