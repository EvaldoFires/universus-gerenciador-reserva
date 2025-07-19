package br.com.universus.gerenciador_reserva.application.usecases.reserva;

import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaIndisponivelException;

import java.util.Optional;

public class CriarReservaUsecase {

    private final ReservaRepository repository;
    private final BuscarMedicoUsecase buscarMedicoUseCase;

    public CriarReservaUsecase(ReservaRepository repository, BuscarMedicoUsecase buscarMedicoUseCase) {
        this.repository = repository;
        this.buscarMedicoUseCase = buscarMedicoUseCase;
    }


    public Reserva cadastrarReserva (Reserva reserva){
        buscarMedicoUseCase.buscarPorCPM(reserva.getMedico().getCrm());
        verificarDisponibilidade(reserva);
        return repository.cadastrarReserva(reserva);
    }

    private void verificarDisponibilidade (Reserva reserva){
        Optional<Reserva> reservaExistente = repository.buscarReservaExistente(reserva.getMedico(),
                reserva.getDataReserva());

        if (reservaExistente.isPresent()) {
            throw new ReservaIndisponivelException("Esse horário de reserva não está disponível.");
        }
    }
}
