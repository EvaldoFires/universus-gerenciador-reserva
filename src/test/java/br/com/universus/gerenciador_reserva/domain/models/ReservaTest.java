package br.com.universus.gerenciador_reserva.domain.models;

import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoDiaException;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoHorarioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class ReservaTest {

    private Medico medicoValido;
    private String nomePaciente;

    @BeforeEach
    void setup() {
        medicoValido = new Medico("12345-6/SP", "Dr. João Pedro Silva");
        nomePaciente = "Ana Carolina";
    }

    @Test
    @DisplayName("Deve criar uma reserva válida em segunda-feira às 16:30")
    void deveCriarReservaValida() {
        LocalDateTime segundaFeira1630 = LocalDateTime.of(2025, 7, 21, 16, 30);

        Reserva reserva = new Reserva(1L, nomePaciente, medicoValido, segundaFeira1630);

        assertThat(reserva.getId()).isEqualTo(1L);
        assertThat(reserva.getNomePaciente()).isEqualTo(nomePaciente);
        assertThat(reserva.getMedico()).isEqualTo(medicoValido);
        assertThat(reserva.getDataReserva()).isEqualTo(segundaFeira1630);
    }

    @Test
    @DisplayName("Deve lançar exceção se reserva for em dia que não é segunda-feira")
    void deveLancarExcecaoSeReservaNaoForSegunda() {
        LocalDateTime tercaFeira = LocalDateTime.of(2025, 7, 22, 16, 30);

        assertThatThrownBy(() ->
                new Reserva(1L, nomePaciente, medicoValido, tercaFeira))
                .isInstanceOf(ReservaForaDoDiaException.class)
                .hasMessageContaining("As reservas só podem ser feitas às segundas-feiras");
    }

    @Test
    @DisplayName("Deve lançar exceção se o horário for antes das 16h")
    void deveLancarExcecaoSeHorarioAntesDas16() {
        LocalDateTime segunda1530 = LocalDateTime.of(2025, 7, 21, 15, 30);

        assertThatThrownBy(() ->
                new Reserva(1L, nomePaciente, medicoValido, segunda1530))
                .isInstanceOf(ReservaForaDoHorarioException.class)
                .hasMessageContaining("Horário fora do intervalo permitido");
    }

    @Test
    @DisplayName("Deve lançar exceção se o horário for depois das 18:30")
    void deveLancarExcecaoSeHorarioDepoisDas1830() {
        LocalDateTime segunda1900 = LocalDateTime.of(2025, 7, 21, 19, 0);

        assertThatThrownBy(() ->
                new Reserva(1L, nomePaciente, medicoValido, segunda1900))
                .isInstanceOf(ReservaForaDoHorarioException.class)
                .hasMessageContaining("Horário fora do intervalo permitido");
    }

    @Test
    @DisplayName("Deve lançar exceção se o horário não for múltiplo de 30 minutos")
    void deveLancarExcecaoSeHorarioInvalido() {
        LocalDateTime segunda1745 = LocalDateTime.of(2025, 7, 21, 17, 45);

        assertThatThrownBy(() ->
                new Reserva(1L, nomePaciente, medicoValido, segunda1745))
                .isInstanceOf(ReservaForaDoHorarioException.class)
                .hasMessageContaining("Horários válidos apenas de 30 em 30 minutos");
    }
}

