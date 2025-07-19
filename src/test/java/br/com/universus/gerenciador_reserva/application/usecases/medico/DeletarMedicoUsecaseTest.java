package br.com.universus.gerenciador_reserva.application.usecases.medico;

import br.com.universus.gerenciador_reserva.application.gateways.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletarMedicoUsecaseTest {

    @Mock
    private MedicoRepository repository;

    private DeletarMedicoUsecase deletarMedicoUsecase;

    @BeforeEach
    void setup() {
        deletarMedicoUsecase = new DeletarMedicoUsecase(repository);
    }

    @Test
    void deveDeletarMedicoPorCrmComSucesso() {
        String crm = "12345-6/SP";

        deletarMedicoUsecase.deletarPorCPF(crm);

        verify(repository).deletarPorCRM(crm);
    }
}

