package br.com.universus.gerenciador_reserva.adapter.mapper;

import br.com.universus.gerenciador_reserva.adapter.dto.medico.MedicoDTO;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public MedicoDTO toDTO(Medico medico) {
        return new MedicoDTO(medico.getCrm(),
                medico.getNome());
    }

    public Medico toDomain(MedicoDTO dto) {
        return new Medico(
                dto.crm(),
                dto.nome()
        );
    }

    public MedicoEntity toEntity(Medico medico) {
        return new MedicoEntity(medico.getCrm(),
                medico.getNome());
    }

    public Medico toDomain(MedicoEntity entity) {
        return new Medico(
                entity.getCrm(),
                entity.getNome());
    }


}
