package com.dailycircular.dailycircular.exception;

import com.dailycircular.dailycircular.exception.ApplicationUserExceptions.ApplicationUserNameNotFoundException;
import com.dailycircular.dailycircular.exception.ApplicationUserExceptions.ApplicationUserNameNotFoundExceptionResponse;
import com.dailycircular.dailycircular.exception.ApplicationUserRegistrationExceptions.EmailVerificationFailedException;
import com.dailycircular.dailycircular.exception.ApplicationUserRegistrationExceptions.EmailVerificationFailedExceptionResponse;
import com.dailycircular.dailycircular.exception.ApplicationUserRegistrationExceptions.UserEmailAlreadyRegisteredException;
import com.dailycircular.dailycircular.exception.ApplicationUserRegistrationExceptions.UserEmailAlreadyRegisteredExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleEntityIdNotFoundException(EntityIdNotFoundException ex, WebRequest request) {
        EntityIdNotFoundExceptionResponse exceptionResponse = new EntityIdNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserEmailAlreadyRegisteredException(UserEmailAlreadyRegisteredException ex, WebRequest request) {
        UserEmailAlreadyRegisteredExceptionResponse exceptionResponse = new UserEmailAlreadyRegisteredExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleApplicationUserNameNotFoundException(ApplicationUserNameNotFoundException ex, WebRequest request) {
        ApplicationUserNameNotFoundExceptionResponse exceptionResponse = new ApplicationUserNameNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleEmailVerificationFailedException(EmailVerificationFailedException ex, WebRequest request) {
        EmailVerificationFailedExceptionResponse exceptionResponse = new EmailVerificationFailedExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
