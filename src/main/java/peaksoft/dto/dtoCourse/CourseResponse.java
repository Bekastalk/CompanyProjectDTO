package peaksoft.dto.dtoCourse;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CourseResponse {
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;


    public CourseResponse(Long id, String courseName, LocalDate dateOfStart, String description) {
        this.id = id;
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }

}
