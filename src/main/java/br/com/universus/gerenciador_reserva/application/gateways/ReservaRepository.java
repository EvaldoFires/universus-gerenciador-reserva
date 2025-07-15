package br.com.universus.gerenciador_reserva.application.gateways;

import br.com.universus.gerenciador_reserva.domain.models.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ReservaRepository {

    Reserva cadastrarReserva(Reserva reserva);

    List<LocalDateTime> buscaReservasPorMedicoEData(String nomeMedico, LocalDate dataReserva);

    Optional<Reserva> buscarReservaExistente(String nomeMedico, LocalDateTime dataReserva);

}
