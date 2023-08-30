package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class SimpleResponse {
    private HttpStatus httpStatus;
    private String message;
}
