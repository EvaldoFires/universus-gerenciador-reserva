package br.com.universus.gerenciador_reserva.domain.models;

import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoDiaException;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoHorarioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    private Medico medico;

    @BeforeEach
    void setup(){
        medico = gerarMedico();
    }


    @Test
    void deveCriarReservaComDataValida() {
        // Arrange
        LocalDateTime data = LocalDate.of(2025, 7, 21).atTime(16, 30); // Segunda-feira, horário válido

        // Act
        Reserva reserva = new Reserva(1L, "João", medico, data);

        // Assert
        assertEquals("João", reserva.getNomePaciente());
        assertEquals(medico, reserva.getMedico());
        assertEquals(data, reserva.getDataReserva());
    }

    @Test
    void deveLancarExcecaoSeNaoForSegundaFeira() {
        // Sexta-feira
        LocalDateTime sexta = LocalDate.of(2025, 7, 25).atTime(16, 30);

        ReservaForaDoDiaException ex = assertThrows(
                ReservaForaDoDiaException.class,
                () -> new Reserva(1L, "João", medico, sexta)
        );

        assertEquals("As reservas só podem ser feitas às segundas-feiras.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeHoraForAntesDas16h() {
        LocalDateTime data = LocalDate.of(2025, 7, 21).atTime(15, 30); // Segunda, mas fora do horário

        ReservaForaDoHorarioException ex = assertThrows(
                ReservaForaDoHorarioException.class,
                () -> new Reserva(1L, "Maria", medico, data)
        );

        assertTrue(ex.getMessage().contains("Horário fora do intervalo permitido"));
    }

    @Test
    void deveLancarExcecaoSeHoraForDepoisDas18h30() {
        LocalDateTime data = LocalDate.of(2025, 7, 21).atTime(19, 0);

        ReservaForaDoHorarioException ex = assertThrows(
                ReservaForaDoHorarioException.class,
                () -> new Reserva(1L, "Maria", medico, data)
        );

        assertTrue(ex.getMessage().contains("Horário fora do intervalo permitido"));
    }

    @Test
    void deveLancarExcecaoSeMinutoForDiferenteDeZeroOuTrinta() {
        LocalDateTime data = LocalDate.of(2025, 7, 21).atTime(17, 15); // Segunda, mas 15 minutos

        ReservaForaDoHorarioException ex = assertThrows(
                ReservaForaDoHorarioException.class,
                () -> new Reserva(1L, "Pedro", medico, data)
        );

        assertEquals("Horários válidos apenas de 30 em 30 minutos.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeNomePacienteForNull() {
        LocalDateTime data = LocalDate.of(2025, 7, 21).atTime(16, 0);

        assertThrows(
                NullPointerException.class,
                () -> new Reserva(1L, null, medico, data)
        );
    }

    @Test
    void deveLancarExcecaoSeNomeMedicoForNull() {
        LocalDateTime data = LocalDate.of(2025, 7, 21).atTime(16, 0);

        assertThrows(
                NullPointerException.class,
                () -> new Reserva(1L, "Lucas", null, data)
        );
    }

    @Test
    void deveLancarExcecaoSeDataForNull() {
        assertThrows(
                NullPointerException.class,
                () -> new Reserva(1L, "Lucas", medico, null)
        );
    }
}

