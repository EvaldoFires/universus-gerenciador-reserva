package br.com.universus.gerenciador_reserva.domain.models;

import br.com.universus.gerenciador_reserva.infra.exceptions.CRMForaDoPadraoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MedicoTest {

    private String nomeValido;

    @BeforeEach
    void setup() {
        nomeValido = "Dr. João Pedro Silva";
    }

    @Test
    @DisplayName("Deve criar um médico com CRM válido")
    void deveCriarMedicoComCrmValido() {
        String crmValido = "12345-6/SP";

        Medico medico = new Medico(crmValido, nomeValido);

        assertThat(medico.getCrm()).isEqualTo(crmValido);
        assertThat(medico.getNome()).isEqualTo(nomeValido);
    }

    @Test
    @DisplayName("Deve lançar exceção se o CRM for nulo")
    void deveLancarExcecaoSeCrmForNulo() {
        assertThatThrownBy(() -> new Medico(null, nomeValido))
                .isInstanceOf(CRMForaDoPadraoException.class)
                .hasMessageContaining("CRM não pode ser nulo ou vazio");
    }

    @Test
    @DisplayName("Deve lançar exceção se o CRM for vazio")
    void deveLancarExcecaoSeCrmForVazio() {
        assertThatThrownBy(() -> new Medico("   ", nomeValido))
                .isInstanceOf(CRMForaDoPadraoException.class)
                .hasMessageContaining("CRM não pode ser nulo ou vazio");
    }

    @Test
    @DisplayName("Deve lançar exceção se o CRM estiver fora do padrão")
    void deveLancarExcecaoSeCrmForInvalido() {
        assertThatThrownBy(() -> new Medico("ABC-123/SP", nomeValido))
                .isInstanceOf(CRMForaDoPadraoException.class)
                .hasMessageContaining("CRM fora do padrão");
    }

    @Test
    @DisplayName("Deve aceitar CRM que respeita o padrão")
    void deveAceitarCrmComPadraoCorreto() {
        String crmValido = "54321-9/MG";
        Medico medico = new Medico(crmValido, nomeValido);

        assertThat(medico.getCrm()).isEqualTo(crmValido);
    }
}
