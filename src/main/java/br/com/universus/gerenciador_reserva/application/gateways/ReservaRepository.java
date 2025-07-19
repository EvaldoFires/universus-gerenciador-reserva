package br.com.universus.gerenciador_reserva.application.gateways;

import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ReservaRepository {

    Reserva cadastrarReserva(Reserva reserva);

    Optional<Reserva> buscarPorId(Long id);

    List<LocalDateTime> buscaReservasPorMedicoEData(Medico medico, LocalDate dataReserva);

    Optional<Reserva> buscarReservaExistente(Medico medico, LocalDateTime dataReserva);

    void deletarPorId(Long id);

}
