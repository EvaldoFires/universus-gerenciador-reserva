package br.com.universus.gerenciador_reserva.application.usecases.reserva;

import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoDiaException;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoHorarioException;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaIndisponivelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarReservaUsecaseTest {

    @Mock
    private ReservaRepository repository;

    private CriarReservaUsecase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CriarReservaUsecase(repository);
    }

    @Test
    void deveCadastrarReservaQuandoHorarioEstiverDisponivel() {
        // Arrange
        Reserva reserva = new Reserva(null, "João", "Dra. Ana",
                LocalDateTime.of(2025, 7, 21, 16, 30)); // Segunda-feira, horário válido

        when(repository.buscarReservaExistente("Dra. Ana", reserva.getDataReserva()))
                .thenReturn(Optional.empty());

        when(repository.cadastrarReserva(reserva)).thenReturn(reserva);

        // Act
        Reserva resultado = useCase.cadastrarReserva(reserva);

        // Assert
        assertNotNull(resultado);
        assertEquals("João", resultado.getNomePaciente());
        verify(repository).buscarReservaExistente("Dra. Ana", reserva.getDataReserva());
        verify(repository).cadastrarReserva(reserva);
    }

    @Test
    void deveLancarExcecaoQuandoHorarioEstiverIndisponivel() {
        // Arrange
        Reserva reserva = new Reserva(null, "Maria", "Dr. Carlos",
                LocalDateTime.of(2025, 7, 28, 17, 0)); // Segunda-feira, horário válido

        when(repository.buscarReservaExistente("Dr. Carlos", reserva.getDataReserva()))
                .thenReturn(Optional.of(reserva)); // Já existe reserva nesse horário

        // Act & Assert
        ReservaIndisponivelException ex = assertThrows(
                ReservaIndisponivelException.class,
                () -> useCase.cadastrarReserva(reserva)
        );

        assertEquals("Esse horário de reserva não está disponível.", ex.getMessage());
        verify(repository).buscarReservaExistente("Dr. Carlos", reserva.getDataReserva());
        verify(repository, never()).cadastrarReserva(any());
    }

    @Test
    void deveLancarExcecaoQuandoHorarioForForaDaSegunda() {
        // Arrange
        LocalDateTime sexta = LocalDateTime.of(2025, 7, 25, 16, 30); // Sexta-feira

        ReservaForaDoDiaException ex = assertThrows(
                ReservaForaDoDiaException.class,
                () -> new Reserva(null, "Joana", "Dra. Carla", sexta)
        );

        assertEquals("As reservas só podem ser feitas às segundas-feiras.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoHorarioForInvalido() {
        // Arrange
        LocalDateTime segundaHorarioInvalido = LocalDateTime.of(2025, 7, 21, 15, 45); // Segunda, mas 15:45

        ReservaForaDoHorarioException ex = assertThrows(
                ReservaForaDoHorarioException.class,
                () -> new Reserva(null, "Pedro", "Dr. Luiz", segundaHorarioInvalido)
        );

        assertTrue(ex.getMessage().contains("Horário fora do intervalo permitido"));
    }
}

