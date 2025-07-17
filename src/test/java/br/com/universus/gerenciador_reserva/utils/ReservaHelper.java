package br.com.universus.gerenciador_reserva.utils;

import br.com.universus.gerenciador_reserva.adapter.dto.ReservaDTO;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.ReservaEntity;

import java.time.LocalDateTime;

public class ReservaHelper {

    public static Reserva gerarReserva(){
        return new Reserva(1L,
                "Alberto",
                "João",
                LocalDateTime.of(2025, 7, 28, 17, 30));
    }

    public static ReservaDTO gerarReservaDto (Reserva reserva){
        return new ReservaDTO(reserva.getId(),
                reserva.getNomePaciente(),
                reserva.getNomeMedico(),
                reserva.getDataReserva());
    }

    public static ReservaEntity gerarReservaEntity (Reserva reserva){
        return new ReservaEntity(reserva.getId(),
                reserva.getNomePaciente(),
                reserva.getNomeMedico(),
                reserva.getDataReserva());
    }


}
