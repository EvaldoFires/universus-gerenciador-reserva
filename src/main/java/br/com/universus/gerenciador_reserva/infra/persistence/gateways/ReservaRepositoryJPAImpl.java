package br.com.universus.gerenciador_reserva.infra.persistence.gateways;


import br.com.universus.gerenciador_reserva.adapter.mapper.ReservaMapper;
import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.ReservaEntity;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.ReservaRepositoryJPA;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservaRepositoryJPAImpl implements ReservaRepository {

    private final ReservaRepositoryJPA repository;
    private final ReservaMapper mapper;

    public ReservaRepositoryJPAImpl(ReservaRepositoryJPA repository, ReservaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Reserva cadastrarReserva(Reserva reserva) {
        ReservaEntity reservaRecebido = mapper.toEntity(reserva);
        ReservaEntity reservaSalvo = repository.save(reservaRecebido);
        return mapper.toDomain(reservaSalvo);
    }



    @Override
    public List<LocalDateTime> buscaReservasPorMedicoEData(String nomeMedico, LocalDate dataReserva) {
        return repository.findAllByNomeMedicoAndDataReserva(nomeMedico, dataReserva);
    }

    @Override
    public Optional<Reserva> buscarReservaExistente(String nomeMedico, LocalDateTime dataReserva) {
        return repository.findByNomeMedicoAndDataReserva(nomeMedico, dataReserva)
                .map(mapper::toDomain);
    }
}
