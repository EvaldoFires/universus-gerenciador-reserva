package br.com.universus.gerenciador_reserva.adapter.controller;

import br.com.universus.gerenciador_reserva.adapter.dto.medico.MedicoDTO;
import br.com.universus.gerenciador_reserva.adapter.mapper.MedicoMapper;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.medico.CriarMedicoUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.medico.DeletarMedicoUsecase;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedicoDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoControllerTest {

    @Mock
    private BuscarMedicoUsecase buscarMedico;

    @Mock
    private CriarMedicoUsecase criarMedico;

    @Mock
    private DeletarMedicoUsecase deletarMedico;

    @Mock
    private MedicoMapper mapper;

    private MedicoController controller;

    private Medico medico;
    private MedicoDTO medicoDTO;

    @BeforeEach
    void setup() {
        controller = new MedicoController(buscarMedico, criarMedico, deletarMedico, mapper);
        medico = gerarMedico();
        medicoDTO = gerarMedicoDto(medico);
    }

    @Test
    void deveBuscarMedicoPorCRM() {
        String crm = medico.getCrm();
        when(buscarMedico.buscarPorCPM(crm)).thenReturn(medico);
        when(mapper.toDTO(medico)).thenReturn(medicoDTO);

        ResponseEntity<MedicoDTO> response = controller.buscarPorCRM(crm);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicoDTO, response.getBody());
        verify(buscarMedico).buscarPorCPM(crm);
        verify(mapper).toDTO(medico);
    }

    @Test
    void deveListarTodosMedicos() {
        List<Medico> medicos = List.of(medico);
        List<MedicoDTO> medicoDTOs = List.of(medicoDTO);

        when(buscarMedico.listarTodos()).thenReturn(medicos);
        when(mapper.toDTO(any(Medico.class))).thenReturn(medicoDTO);

        ResponseEntity<List<MedicoDTO>> response = controller.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicoDTOs.size(), response.getBody().size());
        verify(buscarMedico).listarTodos();
        verify(mapper, times(medicos.size())).toDTO(any(Medico.class));
    }

    @Test
    void deveCriarMedico() {
        when(mapper.toDomain(medicoDTO)).thenReturn(medico);
        when(criarMedico.cadastrarMedico(medico)).thenReturn(medico);
        when(mapper.toDTO(medico)).thenReturn(medicoDTO);

        ResponseEntity<MedicoDTO> response = controller.criarMedico(medicoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(medicoDTO, response.getBody());
        verify(mapper).toDomain(medicoDTO);
        verify(criarMedico).cadastrarMedico(medico);
        verify(mapper).toDTO(medico);
    }

    @Test
    void deveDeletarMedicoPorCRM() {
        String crm = medico.getCrm();

        ResponseEntity<Void> response = controller.deletarMedicoPorCRM(crm);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarMedico).deletarPorCPF(crm);
    }
}

