package br.com.universus.gerenciador_reserva.application.usecases.reserva;

import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.exceptions.RecursoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static br.com.universus.gerenciador_reserva.utils.ReservaHelper.gerarReserva;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarReservaUsecaseTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private BuscarMedicoUsecase buscarMedico;

    @InjectMocks
    private BuscarReservaUsecase usecase;

    private Medico medico;
    private Reserva reserva;

    @BeforeEach
    void setup() {
        medico = gerarMedico();
        reserva = gerarReserva(); // já deve conter o médico correto
    }

    @Test
    void deveBuscarReservaPorIdComSucesso() {
        when(reservaRepository.buscarPorId(1L)).thenReturn(Optional.of(reserva));

        Reserva resultado = usecase.buscarPorId(1L);

        assertThat(resultado).isEqualTo(reserva);
        verify(reservaRepository).buscarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoReservaNaoEncontrada() {
        when(reservaRepository.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usecase.buscarPorId(1L))
                .isInstanceOf(RecursoNaoEncontradoException.class)
                .hasMessageContaining("Reserva não encontrada com id: 1");

        verify(reservaRepository).buscarPorId(1L);
    }

    @Test
    void deveRetornarProximosHorariosDisponiveis() {
        String crm = medico.getCrm();
        LocalDate segunda = LocalDate.of(2025, 7, 21);

        when(buscarMedico.buscarPorCPM(crm)).thenReturn(medico);
        when(reservaRepository.buscaReservasPorMedicoEData(medico, segunda)).thenReturn(List.of(
                segunda.atTime(16, 0),
                segunda.atTime(16, 30)
        ));

        List<List<LocalDateTime>> horarios = usecase.buscarProximosHorariosDisponiveis(crm, segunda);

        assertThat(horarios).isNotEmpty();
        assertThat(horarios.get(0)).doesNotContain(
                segunda.atTime(16, 0),
                segunda.atTime(16, 30)
        );
    }
}

