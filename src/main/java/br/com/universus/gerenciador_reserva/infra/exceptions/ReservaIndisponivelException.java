package br.com.universus.gerenciador_reserva.infra.exceptions;

public class ReservaIndisponivelException extends RuntimeException {
    public ReservaIndisponivelException(String message) {
        super(message);
    }
}
