package br.com.universus.gerenciador_reserva.infra.persistence.gateways;


import br.com.universus.gerenciador_reserva.adapter.mapper.MedicoMapper;
import br.com.universus.gerenciador_reserva.application.gateways.MedicoRepository;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.infra.exceptions.RecursoNaoEncontradoException;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.MedicoRepositoryJPA;

import java.util.List;
import java.util.Optional;

public class MedicoRepositoryJPAImpl implements MedicoRepository {

    private final MedicoRepositoryJPA repository;
    private final MedicoMapper mapper;

    public MedicoRepositoryJPAImpl(MedicoRepositoryJPA repository, MedicoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Medico cadastrarMedico(Medico medico) {
        MedicoEntity medicoRecebido = mapper.toEntity(medico);
        MedicoEntity medicoSalvo = repository.save(medicoRecebido);
        return mapper.toDomain(medicoSalvo);
    }

    @Override
    public Optional<Medico> buscarPorCRM(String crm) {
        return repository.findById(crm)
                .map(mapper::toDomain);
    }

    @Override
    public List<Medico> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deletarPorCRM(String crm) {
        this.buscarPorCRM(crm);
        repository.deleteById(crm);
    }
}
