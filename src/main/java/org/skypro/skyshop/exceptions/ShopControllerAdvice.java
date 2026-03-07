package org.skypro.skyshop.exceptions;

import org.skypro.skyshop.model.error.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        return new ResponseEntity<>(
                ShopError.newError(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()
                ), HttpStatus.NOT_FOUND);
    }

}
