package br.com.universus.gerenciador_reserva.application.usecases.reserva;

import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaIndisponivelException;

import java.util.Optional;

public class CriarReservaUsecase {

    private final ReservaRepository repository;

    public CriarReservaUsecase(ReservaRepository repository) {
        this.repository = repository;
    }


    public Reserva cadastrarReserva (Reserva reserva){
        verificarDisponibilidade(reserva);
        return repository.cadastrarReserva(reserva);
    }

    private void verificarDisponibilidade (Reserva reserva){
        Optional<Reserva> reservaExistente = repository.buscarReservaExistente(reserva.getNomeMedico(),
                reserva.getDataReserva());

        if (reservaExistente.isPresent()) {
            throw new ReservaIndisponivelException("Esse horário de reserva não está disponível.");
        }
    }
}
