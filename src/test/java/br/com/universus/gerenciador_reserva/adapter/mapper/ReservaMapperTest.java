package br.com.universus.gerenciador_reserva.adapter.mapper;


import br.com.universus.gerenciador_reserva.adapter.dto.reserva.ReservaDTO;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.ReservaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static br.com.universus.gerenciador_reserva.utils.ReservaHelper.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservaMapperTest {

    private MedicoMapper medicoMapper;
    private ReservaMapper reservaMapper;
    private Reserva reserva;
    private Medico medico;

    @BeforeEach
    void setup() {
        medicoMapper = new MedicoMapper();
        reservaMapper = new ReservaMapper(medicoMapper);
        medico = gerarMedico();
        reserva = gerarReserva();
    }

    @Test
    void deveConverterDeDomainParaDTO() {
        ReservaDTO dto = reservaMapper.toDTO(reserva);

        assertEquals(reserva.getId(), dto.id());
        assertEquals(reserva.getNomePaciente(), dto.nomePaciente());
        assertEquals(reserva.getMedico().getCrm(), dto.crmMedico());
        assertEquals(reserva.getDataReserva(), dto.dataReserva());
    }

    @Test
    void deveConverterDeDTOParaDomain() {
        ReservaDTO dto = gerarReservaDto(reserva);

        Reserva convertido = reservaMapper.toDomain(dto, medico);

        assertEquals(dto.id(), convertido.getId());
        assertEquals(dto.nomePaciente(), convertido.getNomePaciente());
        assertEquals(dto.dataReserva(), convertido.getDataReserva());
        assertEquals(medico.getCrm(), convertido.getMedico().getCrm());
    }

    @Test
    void deveConverterDeDomainParaEntity() {
        ReservaEntity entity = reservaMapper.toEntity(reserva);

        assertEquals(reserva.getNomePaciente(), entity.getNomePaciente());
        assertEquals(reserva.getDataReserva(), entity.getDataReserva());
        assertEquals(reserva.getMedico().getCrm(), entity.getMedico().getCrm());
    }

    @Test
    void deveConverterDeEntityParaDomain() {
        ReservaEntity entity = gerarReservaEntity(reserva);

        Reserva convertido = reservaMapper.toDomain(entity);

        assertEquals(entity.getId(), convertido.getId());
        assertEquals(entity.getNomePaciente(), convertido.getNomePaciente());
        assertEquals(entity.getDataReserva(), convertido.getDataReserva());
        assertEquals(entity.getMedico().getCrm(), convertido.getMedico().getCrm());
    }
}

