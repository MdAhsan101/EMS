package org.mdahsan101.ExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.mdahsan101.Exceptions.EmployeeAlredyExists;
import org.mdahsan101.Exceptions.InvalidLogin;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(EmployeeAlredyExists.class)
    public ResponseEntity handleEmployeeAlreadyExists(EmployeeAlredyExists employeeAlredyExists)
    {
        return new ResponseEntity(employeeAlredyExists.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException( NoSuchElementException obj)
    {
        return new ResponseEntity("No Such Employee Exists.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLogin.class)
    public ResponseEntity handleInvalidLogin(InvalidLogin invalidLogin)
    {
        return new ResponseEntity(invalidLogin.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity("Change your http method while making request.", HttpStatus.METHOD_NOT_ALLOWED);
    }
}

