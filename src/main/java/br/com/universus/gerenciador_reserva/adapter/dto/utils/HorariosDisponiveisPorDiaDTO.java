package br.com.universus.gerenciador_reserva.adapter.dto.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record HorariosDisponiveisPorDiaDTO(
        LocalDate data,
        List<LocalTime> horarios
) {
}
