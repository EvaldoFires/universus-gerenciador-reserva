package br.com.universus.gerenciador_reserva.adapter.controller;

import br.com.universus.gerenciador_reserva.adapter.mapper.ReservaMapper;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.BuscarReservaUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.CriarReservaUsecase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaControllerTest {

    @InjectMocks
    private ReservaController controller;

    @Mock
    private BuscarReservaUsecase buscarReserva;

    @Mock
    private CriarReservaUsecase criarReserva;

    @Mock
    private ReservaMapper mapper;

    @Test
    void deveBuscarProximosHorariosDisponiveis() {
        // Arrange
        String nomeMedico = "Dr. João";
        LocalDate data = LocalDate.of(2025, 7, 21);
        List<List<LocalDateTime>> horarios = List.of(
                List.of(
                        LocalDateTime.of(2025, 7, 21, 16, 0),
                        LocalDateTime.of(2025, 7, 21, 16, 30)
                )
        );

        when(buscarReserva.buscarProximosHorariosDisponiveis(nomeMedico, data)).thenReturn(horarios);

        // Act
        ResponseEntity<List<List<LocalDateTime>>> response = controller
                .buscarProximosHorariosDisponiveis(nomeMedico, data);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(horarios);

        verify(buscarReserva).buscarProximosHorariosDisponiveis(nomeMedico, data);
    }

    @Test
    void deveCriarReservaComSucesso() {
        // Arrange
//        ReservaDTO inputDto = new ReservaDTO(null, "Paciente", "Dr. João", LocalDateTime.of(2025, 7,21,16,0));
//        Reserva reservaDomain = new Reserva(1L, "Paciente", "Dr. João", LocalDateTime.of(2025, 7, 21, 16, 0));
//        ReservaDTO outputDto = new ReservaDTO(1L, "Paciente", "Dr. João", LocalDateTime.of(2025, 7,21,16,0));
//
//        when(mapper.toDomain(inputDto, )).thenReturn(reservaDomain);
//        when(criarReserva.cadastrarReserva(reservaDomain)).thenReturn(reservaDomain);
//        when(mapper.toDTO(reservaDomain)).thenReturn(outputDto);
//
//        // Act
//        ResponseEntity<ReservaDTO> response = controller.criarReserva(inputDto);
//
//        // Assert
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isEqualTo(outputDto);
//
//        verify(mapper).toDomain(inputDto);
//        verify(criarReserva).cadastrarReserva(reservaDomain);
//        verify(mapper).toDTO(reservaDomain);
    }

}

