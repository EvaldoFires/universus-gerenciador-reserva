package br.com.universus.gerenciador_reserva.adapter.controller.exception;

import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoDiaException;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoHorarioException;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaIndisponivelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservaForaDoHorarioException.class)
    public ResponseEntity<Map<String,Object>> reservaForaDoHorarioExceptionHandler (ReservaForaDoHorarioException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("mensagem", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(ReservaForaDoDiaException.class)
    public ResponseEntity<Map<String,Object>> reservaForaDoDiaExceptionHandler (ReservaForaDoDiaException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("mensagem", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(ReservaIndisponivelException.class)
    public ResponseEntity<Map<String,Object>> reservaIndisponivelExceptionHandler (ReservaIndisponivelException exception){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("mensagem", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
