package br.com.universus.gerenciador_reserva.application.usecases.reserva;

import br.com.universus.gerenciador_reserva.application.gateways.ReservaRepository;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.exceptions.RecursoNaoEncontradoException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BuscarReservaUsecase {

    private final ReservaRepository repository;
    private final BuscarMedicoUsecase buscarMedico;

    public BuscarReservaUsecase(ReservaRepository repository, BuscarMedicoUsecase buscarMedico) {
        this.repository = repository;
        this.buscarMedico = buscarMedico;
    }

    public Reserva buscarPorId(Long id){
        return repository.buscarPorId(id).orElseThrow(() -> new RecursoNaoEncontradoException(
                "Reserva não encontrada com id: " + id
        ));
    }

    public List<List<LocalDateTime>> buscarProximosHorariosDisponiveis(String crm, LocalDate dataInicial) {

        Medico medico = buscarMedico.buscarPorCPM(crm);

        List<List<LocalDateTime>> proximasDatasEHorariosDisponiveis = new ArrayList<>();

        LocalDate data = (dataInicial != null) ? dataInicial : LocalDate.now();

        while (proximasDatasEHorariosDisponiveis.size() < 3) {
            if (data.getDayOfWeek() == DayOfWeek.MONDAY) {

                List<LocalDateTime> horariosReservados = repository
                        .buscaReservasPorMedicoEData(medico, data);

                List<LocalDateTime> horariosDoDia = gerarHorariosValidos(data);

                List<LocalDateTime> horariosDisponiveis = horariosDoDia.stream()
                        .filter(h -> !horariosReservados.contains(h))
                        .toList();

                if (!horariosDisponiveis.isEmpty()) {
                    proximasDatasEHorariosDisponiveis.add(horariosDisponiveis);
                }
            }

            data = data.plusDays(1);
        }

        return proximasDatasEHorariosDisponiveis;
    }

    private List<LocalDateTime> gerarHorariosValidos(LocalDate data) {
        List<LocalDateTime> horarios = new ArrayList<>();
        for (int hora = 16; hora <= 18; hora++) {
            horarios.add(data.atTime(hora, 0));
            horarios.add(data.atTime(hora, 30));
        }
        return horarios;
    }
}
