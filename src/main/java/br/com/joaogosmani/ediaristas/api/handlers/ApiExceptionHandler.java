package br.com.joaogosmani.ediaristas.api.handlers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.joaogosmani.ediaristas.api.dtos.responses.ErrorResponse;
import br.com.joaogosmani.ediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EnderecoServiceException.class)
    public ResponseEntity<Object> handleEnderecoServiceException(
        EnderecoServiceException exception, HttpServletRequest request
    ) {
        var errorResponse = ErrorResponse.builder()
            .status(400)
            .timestamp(LocalDateTime.now())
            .mensagem(exception.getLocalizedMessage())
            .path(request.getRequestURI())
            .build();

            return ResponseEntity.badRequest().body(errorResponse);
    }

}
