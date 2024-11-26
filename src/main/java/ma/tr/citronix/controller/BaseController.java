package ma.tr.citronix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public  interface   BaseController {
    default <T> ResponseEntity<T> responseEntity(T t, HttpStatus status) {
        return new ResponseEntity<>(t, status);
    }
}
