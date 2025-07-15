package br.com.universus.gerenciador_reserva.adapter.dto;

import java.time.LocalDateTime;

public record ReservaDTO(
        Long id,
        String nomePaciente,
        String nomeMedico,
        LocalDateTime dataReserva
) {
}
