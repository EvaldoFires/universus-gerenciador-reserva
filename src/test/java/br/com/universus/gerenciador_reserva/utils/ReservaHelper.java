package br.com.universus.gerenciador_reserva.utils;

import br.com.universus.gerenciador_reserva.adapter.dto.reserva.ReservaDTO;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.ReservaEntity;

import java.time.LocalDateTime;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedicoEntity;

public class ReservaHelper {

    public static Reserva gerarReserva(){
        return new Reserva(1L,
                "Alberto",
                gerarMedico(),
                LocalDateTime.of(2025, 7, 28, 17, 30));
    }

    public static ReservaDTO gerarReservaDto (Reserva reserva){
        return new ReservaDTO(reserva.getId(),
                reserva.getNomePaciente(),
                reserva.getMedico().getNome(),
                reserva.getDataReserva());
    }

    public static ReservaEntity gerarReservaEntity (Reserva reserva){
        return new ReservaEntity(reserva.getId(),
                reserva.getNomePaciente(),
                gerarMedicoEntity(reserva.getMedico()),
                reserva.getDataReserva());
    }


}
