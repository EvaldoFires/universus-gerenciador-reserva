package br.com.universus.gerenciador_reserva.application.usecases.medico;

import br.com.universus.gerenciador_reserva.application.gateways.MedicoRepository;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.infra.exceptions.RecursoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarMedicoUsecaseTest {

    @Mock
    private MedicoRepository repository;

    private BuscarMedicoUsecase buscarMedicoUsecase;

    private Medico medico;

    @BeforeEach
    void setup() {
        buscarMedicoUsecase = new BuscarMedicoUsecase(repository);
        medico = gerarMedico();
    }

    @Test
    void deveBuscarMedicoPorCrmComSucesso() {
        when(repository.buscarPorCRM(medico.getCrm())).thenReturn(Optional.of(medico));

        Medico resultado = buscarMedicoUsecase.buscarPorCPM(medico.getCrm());

        assertEquals(medico, resultado);
        verify(repository).buscarPorCRM(medico.getCrm());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarMedico() {
        String crmInvalido = "00000-0/XX";
        when(repository.buscarPorCRM(crmInvalido)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> buscarMedicoUsecase.buscarPorCPM(crmInvalido));
    }

    @Test
    void deveListarTodosOsMedicos() {
        List<Medico> medicos = List.of(medico);
        when(repository.listarTodos()).thenReturn(medicos);

        List<Medico> resultado = buscarMedicoUsecase.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals(medico, resultado.get(0));
        verify(repository).listarTodos();
    }
}

