package br.com.universus.gerenciador_reserva.infra.config;

import br.com.universus.gerenciador_reserva.adapter.mapper.ReservaMapper;
import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.BuscarReservaUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.CriarReservaUsecase;
import br.com.universus.gerenciador_reserva.infra.persistence.gateways.ReservaRepositoryJPAImpl;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.ReservaRepositoryJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaConfig {

    @Bean
    BuscarReservaUsecase buscarReservaUsecase(ReservaRepository repository){
        return new BuscarReservaUsecase(repository);
    }

    @Bean
    CriarReservaUsecase criarReservaUseCase(ReservaRepository repository){
        return new CriarReservaUsecase(repository);
    }

    @Bean
    ReservaRepositoryJPAImpl criarImplementacaoReservaRepositoryJPA(ReservaRepositoryJPA repositoryJPA,
                                                                    ReservaMapper mapper){
        return new ReservaRepositoryJPAImpl(repositoryJPA, mapper);
    }
}
