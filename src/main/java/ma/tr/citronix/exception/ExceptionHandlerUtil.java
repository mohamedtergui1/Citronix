package ma.tr.citronix.exception;

import jakarta.servlet.http.HttpServletRequest;
import ma.tr.citronix.util.ErrorResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerUtil {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handler(NotFoundException ex, HttpServletRequest request) {

        return new ResponseEntity<>(ErrorResponseUtil.createErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        ), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(NotCompleteProcess.class)
    public ResponseEntity<Map<String, Object>> handler(NotCompleteProcess ex, HttpServletRequest request) {

        return new ResponseEntity<>(ErrorResponseUtil.createErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        ), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(ErrorResponseUtil.createErrorResponse("validation errors", HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), validationErrors), HttpStatus.BAD_REQUEST);
    }

}
