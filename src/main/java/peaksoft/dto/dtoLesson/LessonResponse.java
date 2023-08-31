package peaksoft.dto.dtoLesson;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LessonResponse {
    private Long id;
    private String lessonName;

    public LessonResponse(Long id, String lessonName) {
        this.id = id;
        this.lessonName = lessonName;
    }
}
