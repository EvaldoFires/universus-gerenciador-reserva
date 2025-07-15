package br.com.universus.gerenciador_reserva.infra.exceptions;

public class ReservaForaDoHorarioException extends RuntimeException {
  public ReservaForaDoHorarioException(String message) {
    super(message);
  }
}
