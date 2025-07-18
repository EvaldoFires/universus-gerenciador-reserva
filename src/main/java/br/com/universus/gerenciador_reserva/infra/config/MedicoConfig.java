package br.com.universus.gerenciador_reserva.infra.config;

import br.com.universus.gerenciador_reserva.adapter.mapper.MedicoMapper;
import br.com.universus.gerenciador_reserva.application.gateways.MedicoRepository;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.medico.CriarMedicoUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.medico.DeletarMedicoUsecase;
import br.com.universus.gerenciador_reserva.infra.persistence.gateways.MedicoRepositoryJPAImpl;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.MedicoRepositoryJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicoConfig {
    
    @Bean
    BuscarMedicoUsecase buscarMedicoUseCase(MedicoRepository repository){
        return new BuscarMedicoUsecase(repository);
    }

    @Bean
    CriarMedicoUsecase criarMedicoUseCase(MedicoRepository repository){
        return new CriarMedicoUsecase(repository);
    }

    @Bean
    DeletarMedicoUsecase deletarMedicoUseCase(MedicoRepository repository){
        return new DeletarMedicoUsecase(repository);
    }

    @Bean
    MedicoRepositoryJPAImpl criarImplementacaoMedicoRepositoryJPA(MedicoRepositoryJPA repositoryJPA,
                                                                  MedicoMapper mapper){
        return new MedicoRepositoryJPAImpl(repositoryJPA, mapper);
    }
}
