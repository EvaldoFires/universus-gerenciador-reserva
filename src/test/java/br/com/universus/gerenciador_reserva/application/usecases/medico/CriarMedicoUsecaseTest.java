package br.com.universus.gerenciador_reserva.application.usecases.medico;

import br.com.universus.gerenciador_reserva.application.gateways.MedicoRepository;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarMedicoUsecaseTest {

    @Mock
    private MedicoRepository repository;

    private CriarMedicoUsecase criarMedicoUsecase;

    private Medico medico;

    @BeforeEach
    void setup() {
        criarMedicoUsecase = new CriarMedicoUsecase(repository);
        medico = gerarMedico();
    }

    @Test
    void deveCadastrarMedicoComSucesso() {
        when(repository.cadastrarMedico(medico)).thenReturn(medico);

        Medico resultado = criarMedicoUsecase.cadastrarMedico(medico);

        assertEquals(medico, resultado);
        verify(repository).cadastrarMedico(medico);
    }
}

