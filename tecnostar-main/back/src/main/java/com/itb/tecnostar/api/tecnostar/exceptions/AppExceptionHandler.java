package com.itb.tecnostar.api.tecnostar.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

// Transforma as exceções da aplicação em respostas JSON padronizadas
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private static final ZoneId ZONA_BRASIL = ZoneId.of("America/Sao_Paulo");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalException(Exception ex) {
        ex.printStackTrace(); // detalhe só no console; o cliente recebe mensagem genérica
        return montar("Ocorreu um erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Object> badRequestException(BadRequest ex) {
        return montar(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Object> notFoundException(NotFound ex) {
        return montar(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity<Object> unauthorizedException(Unauthorized ex) {
        return montar(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<Object> montar(String mensagem, HttpStatus status) {
        ErrorMessage erro = new ErrorMessage(LocalDateTime.now(ZONA_BRASIL), new String[]{mensagem}, status);
        return new ResponseEntity<>(erro, new HttpHeaders(), status);
    }
}
