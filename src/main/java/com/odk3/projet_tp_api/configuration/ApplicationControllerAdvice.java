package com.odk3.projet_tp_api.configuration;

import com.odk3.projet_tp_api.configuration.dto.ErrorEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    // Not Found Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody @Valid ErrorEntity handleException(EntityNotFoundException exception){
        // String errorMessage = exception.getMessage();
        return new ErrorEntity(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public @Valid
    @ResponseBody ErrorEntity handleHttpServerErrorException(HttpServerErrorException e){
        return new ErrorEntity(e.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public @Valid
    @ResponseBody ErrorEntity handleHttpClientErrorException(HttpClientErrorException e) {
        // Log the error
        //logger.error(e);

        // Return a 404 Not Found response with a custom message
        return new  ErrorEntity(e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public@Valid
    @ResponseBody ErrorEntity handleAllException(Exception exception){
        return new ErrorEntity(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){
        final  String[] msg = {""};
        exception.getBindingResult().getAllErrors().forEach((error)-> {
            msg[0] += "'"+((FieldError) error).getField()+"' : "+error.getDefaultMessage()+", ";
        });
        return new ErrorEntity(
                msg[0]
        );
    }
}
