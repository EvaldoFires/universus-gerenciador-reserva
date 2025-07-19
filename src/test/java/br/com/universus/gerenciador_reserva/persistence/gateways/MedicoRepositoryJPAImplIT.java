package br.com.universus.gerenciador_reserva.persistence.gateways;


import br.com.universus.gerenciador_reserva.adapter.mapper.MedicoMapper;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;
import br.com.universus.gerenciador_reserva.infra.persistence.gateways.MedicoRepositoryJPAImpl;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.MedicoRepositoryJPA;
import br.com.universus.gerenciador_reserva.utils.MedicoHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@Import({MedicoRepositoryJPAImpl.class, MedicoMapper.class})
class MedicoRepositoryJPAImplIT {

    @Autowired
    private MedicoRepositoryJPAImpl medicoRepository;

    @Autowired
    private MedicoRepositoryJPA repository;

    @Test
    void deveSalvarEMedico() {
        Medico medico = MedicoHelper.gerarMedico();
        Medico salvo = medicoRepository.cadastrarMedico(medico);

        assertNotNull(salvo);
        assertEquals(medico.getCrm(), salvo.getCrm());
    }

    @Test
    void deveBuscarMedicoPorCRM() {
        MedicoEntity entity = MedicoHelper.gerarMedicoEntity(MedicoHelper.gerarMedico());
        repository.save(entity);

        Optional<Medico> encontrado = medicoRepository.buscarPorCRM(entity.getCrm());

        assertTrue(encontrado.isPresent());
        assertEquals(entity.getCrm(), encontrado.get().getCrm());
    }

    @Test
    void deveDeletarMedicoPorCRM() {
        MedicoEntity entity = MedicoHelper.gerarMedicoEntity(MedicoHelper.gerarMedico());
        repository.save(entity);

        medicoRepository.deletarPorCRM(entity.getCrm());

        assertTrue(repository.findById(entity.getCrm()).isEmpty());
    }
}

