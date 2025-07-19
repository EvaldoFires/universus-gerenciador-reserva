package br.com.universus.gerenciador_reserva.infra.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {
  public RecursoNaoEncontradoException(String message) {
    super(message);
  }
}
