package br.com.universus.gerenciador_reserva.application.usecases.medico;


import br.com.universus.gerenciador_reserva.application.gateways.MedicoRepository;
import br.com.universus.gerenciador_reserva.domain.models.Medico;

public class CriarMedicoUsecase {

    private final MedicoRepository repository;

    public CriarMedicoUsecase(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico cadastrarMedico(Medico enfermeiro){
        return repository.cadastrarMedico(enfermeiro);
    }

}
