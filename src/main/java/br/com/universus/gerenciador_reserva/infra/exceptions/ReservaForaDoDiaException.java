package br.com.universus.gerenciador_reserva.infra.exceptions;

public class ReservaForaDoDiaException extends RuntimeException {
    public ReservaForaDoDiaException(String message) {
        super(message);
    }
}
