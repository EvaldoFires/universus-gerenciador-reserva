package br.com.universus.gerenciador_reserva.adapter.controller;

import br.com.universus.gerenciador_reserva.adapter.dto.reserva.ReservaDTO;
import br.com.universus.gerenciador_reserva.adapter.dto.utils.HorariosDisponiveisPorDiaDTO;
import br.com.universus.gerenciador_reserva.adapter.mapper.ReservaMapper;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.BuscarReservaUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.CriarReservaUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.DeletarReservaUsecase;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static br.com.universus.gerenciador_reserva.utils.ReservaHelper.gerarReserva;
import static br.com.universus.gerenciador_reserva.utils.ReservaHelper.gerarReservaDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaControllerTest {

    @Mock
    private BuscarReservaUsecase buscarReserva;

    @Mock
    private CriarReservaUsecase criarReserva;

    @Mock
    private DeletarReservaUsecase deletarReserva;

    @Mock
    private BuscarMedicoUsecase buscarMedico;

    @Mock
    private ReservaMapper mapper;

    private ReservaController controller;

    private Reserva reserva;
    private ReservaDTO reservaDTO;
    private Medico medico;

    @BeforeEach
    void setup() {
        controller = new ReservaController(buscarReserva, criarReserva, deletarReserva, buscarMedico, mapper);
        medico = gerarMedico();
        reserva = gerarReserva();
        reservaDTO = gerarReservaDto(reserva);
    }

    @Test
    void deveBuscarProximosHorariosDisponiveis() {
        String crmMedico = medico.getCrm();
        LocalDate dataInicial = LocalDate.now();

        List<List<LocalDateTime>> horarios = List.of(
                List.of(LocalDateTime.of(2025,7,21,16,30), LocalDateTime.of(2025,7,21,17,30))
        );

        when(buscarReserva.buscarProximosHorariosDisponiveis(crmMedico, dataInicial)).thenReturn(horarios);

        ResponseEntity<List<HorariosDisponiveisPorDiaDTO>> response = controller.buscarProximosHorariosDisponiveis(crmMedico, dataInicial);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(horarios.get(0).get(0).toLocalDate(), response.getBody().get(0).data());
    }

    @Test
    void deveBuscarReservaPorId() {
        Long id = reserva.getId();

        when(buscarReserva.buscarPorId(id)).thenReturn(reserva);
        when(mapper.toDTO(reserva)).thenReturn(reservaDTO);

        ResponseEntity<ReservaDTO> response = controller.buscarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservaDTO, response.getBody());
        verify(buscarReserva).buscarPorId(id);
        verify(mapper).toDTO(reserva);
    }

    @Test
    void deveCriarReserva() {
        when(buscarMedico.buscarPorCPM(reservaDTO.crmMedico())).thenReturn(medico);
        when(mapper.toDomain(reservaDTO, medico)).thenReturn(reserva);
        when(criarReserva.cadastrarReserva(reserva)).thenReturn(reserva);
        when(mapper.toDTO(reserva)).thenReturn(reservaDTO);

        ResponseEntity<ReservaDTO> response = controller.criarReserva(reservaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reservaDTO, response.getBody());
        verify(buscarMedico).buscarPorCPM(reservaDTO.crmMedico());
        verify(mapper).toDomain(reservaDTO, medico);
        verify(criarReserva).cadastrarReserva(reserva);
        verify(mapper).toDTO(reserva);
    }

    @Test
    void deveDeletarReservaPorId() {
        Long id = reserva.getId();

        ResponseEntity<Void> response = controller.deletarPorId(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarReserva).deletarPorId(id);
    }
}


