package br.com.universus.gerenciador_reserva.application.usecases.reserva;

import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarReservaUsecaseTest {

    @Mock
    private ReservaRepository repository;

    private BuscarReservaUsecase useCase;

    @BeforeEach
    void setUp() {
        useCase = new BuscarReservaUsecase(repository);
    }

    @Test
    void deveRetornarProximosHorariosDisponiveisParaTresSegundasFeiras() {
        // Arrange
        String nomeMedico = "Dr. House";
        LocalDate primeiraSegunda = proximaSegunda(LocalDate.now());

        // Simula 3 segundas com todos horários disponíveis
        for (int i = 0; i < 3; i++) {
            LocalDate data = primeiraSegunda.plusWeeks(i);
            when(repository.buscaReservasPorMedicoEData(nomeMedico, data))
                    .thenReturn(Collections.emptyList()); // Nenhuma reserva
        }

        // Act
        List<List<LocalDateTime>> resultado = useCase.buscarProximosHorariosDisponiveis(nomeMedico, null);

        // Assert
        assertEquals(3, resultado.size(), "Deveria retornar 3 semanas com horários disponíveis");

        for (List<LocalDateTime> horarios : resultado) {
            assertFalse(horarios.isEmpty(), "Cada segunda-feira deveria conter horários disponíveis");
            assertEquals(6, horarios.size(), "Devem existir 6 horários por segunda (16:00 às 18:30)");
        }

        // Verifica que chamou o repositório exatamente 3 vezes
        for (int i = 0; i < 3; i++) {
            verify(repository).buscaReservasPorMedicoEData(nomeMedico, primeiraSegunda.plusWeeks(i));
        }
    }

    // Utilitários

    private LocalDate proximaSegunda(LocalDate base) {
        while (base.getDayOfWeek() != DayOfWeek.MONDAY) {
            base = base.plusDays(1);
        }
        return base;
    }

}
