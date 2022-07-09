package pl.sda.arppl4.springrental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sda.arppl4.springrental.exception.CarNotAvailableException;
import pl.sda.arppl4.springrental.model.dto.ErrorMessageResponse;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice

public class ErrorHandlingController {

    // Mapping

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleEntityNotFound(EntityNotFoundException exception){
        // Ramka HTTP posiada:
        // - adres (ip:port/contex/mapping)
        // - metoda http
        // - nagłówki
        // - body
        // - status
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageResponse(exception.getMessage()));
    }

    @ExceptionHandler(CarNotAvailableException.class)
    @ResponseStatus(HttpStatus.GONE)
    public ErrorMessageResponse handleCarNotAvailable(CarNotAvailableException exception) {
        return new ErrorMessageResponse(exception.getMessage());
    }



}
