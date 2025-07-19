package br.com.universus.gerenciador_reserva.domain.models;


import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoDiaException;
import br.com.universus.gerenciador_reserva.infra.exceptions.ReservaForaDoHorarioException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reserva {

    private Long id;
    private String nomePaciente;
    private Medico medico;
    private LocalDateTime dataReserva;

    public Reserva(Long id, String nomePaciente, Medico medico, LocalDateTime dataReserva) {
        validarHorario(dataReserva);
        this.id = id;
        this.nomePaciente = Objects.requireNonNull(nomePaciente);
        this.medico = Objects.requireNonNull(medico);
        this.dataReserva = Objects.requireNonNull(dataReserva);
    }

    private void validarHorario(LocalDateTime data) {
        if (!data.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            throw new ReservaForaDoDiaException("As reservas só podem ser feitas às segundas-feiras.");
        }

        int hora = data.getHour();
        int minuto = data.getMinute();

        if (hora < 16 || hora >= 19 ) {
            throw new ReservaForaDoHorarioException("Horário fora do intervalo permitido: 16h às 19h.");
        }

        if (minuto != 0 && minuto != 30) {
            throw new ReservaForaDoHorarioException("Horários válidos apenas de 30 em 30 minutos.");
        }
    }

    // Getters
    public Long getId() { return id; }
    public String getNomePaciente() { return nomePaciente; }
    public Medico getMedico() { return medico; }
    public LocalDateTime getDataReserva() { return dataReserva; }
}
