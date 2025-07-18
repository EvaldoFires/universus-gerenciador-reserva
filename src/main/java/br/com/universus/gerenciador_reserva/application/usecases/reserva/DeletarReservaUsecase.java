package br.com.universus.gerenciador_reserva.application.usecases.reserva;


import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;

public class DeletarReservaUsecase {

    private final ReservaRepository repository;
    private final BuscarReservaUsecase buscarReserva;

    public DeletarReservaUsecase(ReservaRepository repository, BuscarReservaUsecase buscarReserva) {
        this.repository = repository;
        this.buscarReserva = buscarReserva;
    }

    public void deletarPorId(Long id){
        buscarReserva.buscarPorId(id);
        repository.deletarPorId(id);
    }
}
