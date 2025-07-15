package br.com.universus.gerenciador_reserva.adapter.mapper;

import br.com.universus.gerenciador_reserva.adapter.dto.ReservaDTO;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.ReservaEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {


    public ReservaDTO toDTO(Reserva reserva) {
        return new ReservaDTO(reserva.getId(),
                reserva.getNomePaciente(),
                reserva.getNomeMedico(),
                reserva.getDataReserva());
    }

    public Reserva toDomain(ReservaDTO dto) {
        return new Reserva(dto.id(),
                dto.nomePaciente(),
                dto.nomeMedico(),
                dto.dataReserva());
    }

    public ReservaEntity toEntity(Reserva reserva) {
        ReservaEntity entity = new ReservaEntity();
        entity.setNomePaciente(reserva.getNomePaciente());
        entity.setNomeMedico(reserva.getNomeMedico());
        entity.setDataReserva(reserva.getDataReserva());
        return entity;
    }

    public Reserva toDomain(ReservaEntity entity) {
        return new Reserva(entity.getId(),
                entity.getNomePaciente(),
                entity.getNomeMedico(),
                entity.getDataReserva()
        );
    }


}
