package peaksoft.dto.dtoCourse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CourseRequest {
    private String courseName;
    private String description;
}
