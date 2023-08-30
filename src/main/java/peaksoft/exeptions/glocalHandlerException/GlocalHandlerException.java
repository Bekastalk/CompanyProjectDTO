package peaksoft.exeptions.glocalHandlerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.exeptions.ExceptionResponce;
import peaksoft.exeptions.InvalidEmailException;
import peaksoft.exeptions.NotFoundException;

@RestControllerAdvice
public class GlocalHandlerException {

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponce invalidEmailException(InvalidEmailException i) {
        return new ExceptionResponce(HttpStatus.BAD_REQUEST, i.getClass().getSimpleName(), i.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ExceptionResponce notFound(NotFoundException n) {
        return new ExceptionResponce(HttpStatus.FOUND, n.getClass().getSimpleName(), n.getMessage());
    }
}
