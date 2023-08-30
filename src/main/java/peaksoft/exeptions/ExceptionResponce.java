package peaksoft.exeptions;


import org.springframework.http.HttpStatus;

public record ExceptionResponce(HttpStatus httpStatus, String exeptionClassName, String message) {

}
