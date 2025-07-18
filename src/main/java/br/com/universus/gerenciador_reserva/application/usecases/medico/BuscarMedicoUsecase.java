package br.com.universus.gerenciador_reserva.application.usecases.medico;


import br.com.universus.gerenciador_reserva.application.gateways.MedicoRepository;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.infra.exceptions.RecursoNaoEncontradoException;

import java.util.List;

public class BuscarMedicoUsecase {

    private final MedicoRepository repository;

    public BuscarMedicoUsecase(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico buscarPorCPM(String crm){
        return repository.buscarPorCRM(crm).orElseThrow(() -> new RecursoNaoEncontradoException(
                "Medico não encontrado com CRM: " + crm
        ));
    }

    public List<Medico> listarTodos(){
        return repository.listarTodos();
    }
}
