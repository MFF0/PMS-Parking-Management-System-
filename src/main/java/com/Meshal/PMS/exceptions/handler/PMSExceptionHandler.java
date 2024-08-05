package com.Meshal.PMS.exceptions.handler;

import com.Meshal.PMS.exceptions.UserNotFoundException;
import com.Meshal.PMS.exceptions.model.FailureResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Component
@ControllerAdvice
public class PMSExceptionHandler {

    @ExceptionHandler(value =UserNotFoundException.class)
    public ResponseEntity<FailureResponse> handleUserNotFoundException(UserNotFoundException ex) {
        FailureResponse failureResponse = FailureResponse.builder()
                .message(ex.getMessage()).result(false).build();
        return ResponseEntity.badRequest().body(failureResponse);
    }
}
