package br.com.universus.gerenciador_reserva.infra.persistence.gateways;


import br.com.universus.gerenciador_reserva.adapter.mapper.MedicoMapper;
import br.com.universus.gerenciador_reserva.adapter.mapper.ReservaMapper;
import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.ReservaEntity;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.ReservaRepositoryJPA;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservaRepositoryJPAImpl implements ReservaRepository {

    private final ReservaRepositoryJPA repository;
    private final ReservaMapper reservaMapper;
    private final MedicoMapper medicoMapper;

    public ReservaRepositoryJPAImpl(ReservaRepositoryJPA repository, ReservaMapper mapper, MedicoMapper medicoMapper) {
        this.repository = repository;
        this.reservaMapper = mapper;
        this.medicoMapper = medicoMapper;
    }

    @Override
    public Reserva cadastrarReserva(Reserva reserva) {
        ReservaEntity reservaRecebido = reservaMapper.toEntity(reserva);
        ReservaEntity reservaSalvo = repository.save(reservaRecebido);
        return reservaMapper.toDomain(reservaSalvo);
    }

    @Override
    public List<LocalDateTime> buscaReservasPorMedicoEData(Medico medico, LocalDate dataReserva) {
        MedicoEntity medicoEntity = medicoMapper.toEntity(medico);
        return repository.findAllByMedicoAndDataReserva(medicoEntity, dataReserva);
    }

    @Override
    public Optional<Reserva> buscarPorId(Long id){
        return repository.findById(id).map(reservaMapper::toDomain);
    }

    @Override
    public Optional<Reserva> buscarReservaExistente(Medico medico, LocalDateTime dataReserva) {
        MedicoEntity medicoEntity = medicoMapper.toEntity(medico);
        return repository.findByMedicoAndDataReserva(medicoEntity, dataReserva)
                .map(reservaMapper::toDomain);
    }

    @Override
    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
}
