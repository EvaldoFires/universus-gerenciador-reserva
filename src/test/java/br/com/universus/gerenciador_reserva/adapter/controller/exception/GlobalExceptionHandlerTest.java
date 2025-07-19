package br.com.universus.gerenciador_reserva.adapter.controller.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExcecaoFakeController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveTratarReservaForaDoHorarioException() throws Exception {
        mockMvc.perform(get("/fake/reserva-fora-do-horario"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("Horário inválido"));
    }

    @Test
    void deveTratarReservaForaDoDiaException() throws Exception {
        mockMvc.perform(get("/fake/reserva-fora-do-dia"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("Dia inválido"));
    }

    @Test
    void deveTratarReservaIndisponivelException() throws Exception {
        mockMvc.perform(get("/fake/reserva-indisponivel"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("Horário não disponível"));
    }

    @Test
    void deveTratarCRMForaDoPadraoException() throws Exception {
        mockMvc.perform(get("/fake/crm-invalido"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensagem").value("CRM inválido"));
    }

    @Test
    void deveTratarRecursoNaoEncontradoException() throws Exception {
        mockMvc.perform(get("/fake/recurso-nao-encontrado"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem").value("Recurso não encontrado"));
    }
}

