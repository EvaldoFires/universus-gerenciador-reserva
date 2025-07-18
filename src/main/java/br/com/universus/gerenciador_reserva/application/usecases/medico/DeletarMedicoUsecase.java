package br.com.universus.gerenciador_reserva.application.usecases.medico;


import br.com.universus.gerenciador_reserva.application.gateways.MedicoRepository;

public class DeletarMedicoUsecase {

    private final MedicoRepository repository;

    public DeletarMedicoUsecase(MedicoRepository repository) {
        this.repository = repository;
    }

    public void deletarPorCPF(String cpf){
        repository.deletarPorCRM(cpf);
    }
}
