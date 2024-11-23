package ma.youcode.citronix.exceptions;

import ma.senane.utilities.dto.ErrorDTO;
import ma.senane.utilities.exceptions.AbstractGlobalHandlerException;
import ma.youcode.citronix.exceptions.farm.FarmNotFoundException;
import ma.youcode.citronix.exceptions.field.FieldNotFoundException;
import ma.youcode.citronix.exceptions.field.FieldSurfaceToLargeException;
import ma.youcode.citronix.exceptions.field.MaxFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static ma.senane.utilities.utils.Response.error;

@RestControllerAdvice
public class GlobalHandlerException extends AbstractGlobalHandlerException {

    @ExceptionHandler(FarmNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleFarmNotFoundException(FarmNotFoundException e) {
        return error(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleFieldNotFoundException(FieldNotFoundException e) {
        return error(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(MaxFieldsException.class)
    public ResponseEntity<ErrorDTO> handleMaxFieldsException(MaxFieldsException e) {
        return error(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(FieldSurfaceToLargeException.class)
    public ResponseEntity<ErrorDTO> handleFieldSurfaceToLargeException(FieldSurfaceToLargeException e) {
        return error(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }



}
