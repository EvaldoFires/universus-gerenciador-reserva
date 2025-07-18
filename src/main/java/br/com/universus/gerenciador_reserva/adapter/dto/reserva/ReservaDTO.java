package br.com.universus.gerenciador_reserva.adapter.dto.reserva;

import java.time.LocalDateTime;

public record ReservaDTO(
        Long id,
        String nomePaciente,
        String crmMedico,
        LocalDateTime dataReserva
) {
}
