package br.com.universus.gerenciador_reserva.infra.config;

import br.com.universus.gerenciador_reserva.adapter.mapper.MedicoMapper;
import br.com.universus.gerenciador_reserva.adapter.mapper.ReservaMapper;
import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.BuscarReservaUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.CriarReservaUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.DeletarReservaUsecase;
import br.com.universus.gerenciador_reserva.infra.persistence.gateways.ReservaRepositoryJPAImpl;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.ReservaRepositoryJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaConfig {

    @Bean
    BuscarReservaUsecase buscarReservaUsecase(ReservaRepository repository, BuscarMedicoUsecase buscarMedico){
        return new BuscarReservaUsecase(repository, buscarMedico);
    }

    @Bean
    CriarReservaUsecase criarReservaUseCase(ReservaRepository repository, BuscarMedicoUsecase buscarMedicoUseCase){
        return new CriarReservaUsecase(repository, buscarMedicoUseCase);
    }

    @Bean
    DeletarReservaUsecase deletarReservaUsecase(ReservaRepository repository, BuscarReservaUsecase buscarReserva ){
        return new DeletarReservaUsecase(repository, buscarReserva);
    }

    @Bean
    ReservaRepositoryJPAImpl criarImplementacaoReservaRepositoryJPA(ReservaRepositoryJPA repositoryJPA,
                                                                    ReservaMapper reservaMapper,
                                                                    MedicoMapper medicoMapper){
        return new ReservaRepositoryJPAImpl(repositoryJPA, reservaMapper, medicoMapper);
    }


}
