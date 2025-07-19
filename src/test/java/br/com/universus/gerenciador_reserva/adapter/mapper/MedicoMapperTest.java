package br.com.universus.gerenciador_reserva.adapter.mapper;

import br.com.universus.gerenciador_reserva.adapter.dto.medico.MedicoDTO;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.*;
import static org.junit.jupiter.api.Assertions.*;

class MedicoMapperTest {

    private MedicoMapper mapper;
    private Medico medico;

    @BeforeEach
    void setup() {
        mapper = new MedicoMapper();
        medico = gerarMedico();
    }

    @Test
    void deveConverterDeDomainParaDTO() {
        MedicoDTO dto = mapper.toDTO(medico);

        assertEquals(medico.getCrm(), dto.crm());
        assertEquals(medico.getNome(), dto.nome());
    }

    @Test
    void deveConverterDeDTOParaDomain() {
        MedicoDTO dto = gerarMedicoDto(medico);

        Medico convertido = mapper.toDomain(dto);

        assertEquals(dto.crm(), convertido.getCrm());
        assertEquals(dto.nome(), convertido.getNome());
    }

    @Test
    void deveConverterDeDomainParaEntity() {
        MedicoEntity entity = mapper.toEntity(medico);

        assertEquals(medico.getCrm(), entity.getCrm());
        assertEquals(medico.getNome(), entity.getNome());
    }

    @Test
    void deveConverterDeEntityParaDomain() {
        MedicoEntity entity = gerarMedicoEntity(medico);

        Medico convertido = mapper.toDomain(entity);

        assertEquals(entity.getCrm(), convertido.getCrm());
        assertEquals(entity.getNome(), convertido.getNome());
    }
}
