package br.com.universus.gerenciador_reserva.adapter.controller.exception;

import br.com.universus.gerenciador_reserva.infra.exceptions.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fake")
class ExcecaoFakeController {

    @GetMapping("/reserva-fora-do-horario")
    public void reservaForaDoHorario() {
        throw new ReservaForaDoHorarioException("Horário inválido");
    }

    @GetMapping("/reserva-fora-do-dia")
    public void reservaForaDoDia() {
        throw new ReservaForaDoDiaException("Dia inválido");
    }

    @GetMapping("/reserva-indisponivel")
    public void reservaIndisponivel() {
        throw new ReservaIndisponivelException("Horário não disponível");
    }

    @GetMapping("/crm-invalido")
    public void crmInvalido() {
        throw new CRMForaDoPadraoException("CRM inválido");
    }

    @GetMapping("/recurso-nao-encontrado")
    public void recursoNaoEncontrado() {
        throw new RecursoNaoEncontradoException("Recurso não encontrado");
    }
}

