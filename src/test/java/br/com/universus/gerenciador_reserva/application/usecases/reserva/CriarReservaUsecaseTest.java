package br.com.universus.gerenciador_reserva.application.usecases.reserva;

import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaIndisponivelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.universus.gerenciador_reserva.utils.ReservaHelper.gerarReserva;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarReservaUsecaseTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private BuscarMedicoUsecase buscarMedicoUsecase;

    @InjectMocks
    private CriarReservaUsecase usecase;

    private Reserva reserva;

    @BeforeEach
    void setup() {
        reserva = gerarReserva(); // já contém médico e horário válido
    }

    @Test
    void deveCadastrarReservaComSucesso() {
        when(buscarMedicoUsecase.buscarPorCPM(reserva.getMedico().getCrm()))
                .thenReturn(reserva.getMedico());

        when(reservaRepository.buscarReservaExistente(reserva.getMedico(), reserva.getDataReserva()))
                .thenReturn(Optional.empty());

        when(reservaRepository.cadastrarReserva(reserva))
                .thenReturn(reserva);

        Reserva resultado = usecase.cadastrarReserva(reserva);

        assertThat(resultado).isEqualTo(reserva);

        verify(buscarMedicoUsecase).buscarPorCPM(reserva.getMedico().getCrm());
        verify(reservaRepository).buscarReservaExistente(reserva.getMedico(), reserva.getDataReserva());
        verify(reservaRepository).cadastrarReserva(reserva);
    }

    @Test
    void deveLancarExcecaoQuandoHorarioEstiverIndisponivel() {
        when(buscarMedicoUsecase.buscarPorCPM(reserva.getMedico().getCrm()))
                .thenReturn(reserva.getMedico());

        when(reservaRepository.buscarReservaExistente(reserva.getMedico(), reserva.getDataReserva()))
                .thenReturn(Optional.of(reserva));

        assertThatThrownBy(() -> usecase.cadastrarReserva(reserva))
                .isInstanceOf(ReservaIndisponivelException.class)
                .hasMessageContaining("Esse horário de reserva não está disponível.");

        verify(buscarMedicoUsecase).buscarPorCPM(reserva.getMedico().getCrm());
        verify(reservaRepository).buscarReservaExistente(reserva.getMedico(), reserva.getDataReserva());
        verify(reservaRepository, never()).cadastrarReserva(any());
    }
}


