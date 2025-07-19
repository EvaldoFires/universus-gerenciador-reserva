package br.com.universus.gerenciador_reserva.adapter.mapper;

import br.com.universus.gerenciador_reserva.adapter.dto.reserva.ReservaDTO;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.ReservaEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {


    private final MedicoMapper medicoMapper;

    public ReservaMapper(MedicoMapper medicoMapper) {
        this.medicoMapper = medicoMapper;
    }

    public ReservaDTO toDTO(Reserva reserva) {
        return new ReservaDTO(reserva.getId(),
                reserva.getNomePaciente(),
                reserva.getMedico().getCrm(),
                reserva.getDataReserva());
    }

    public Reserva toDomain(ReservaDTO dto, Medico medico) {
        return new Reserva(dto.id(),
                dto.nomePaciente(),
                medico,
                dto.dataReserva());
    }

    public ReservaEntity toEntity(Reserva reserva) {
        ReservaEntity entity = new ReservaEntity();
        entity.setNomePaciente(reserva.getNomePaciente());
        entity.setMedico(medicoMapper.toEntity(reserva.getMedico()));
        entity.setDataReserva(reserva.getDataReserva());
        return entity;
    }

    public Reserva toDomain(ReservaEntity entity) {
        return new Reserva(entity.getId(),
                entity.getNomePaciente(),
                medicoMapper.toDomain(entity.getMedico()),
                entity.getDataReserva()
        );
    }


}
