package br.com.universus.gerenciador_reserva.persistence.gateways;

import br.com.universus.gerenciador_reserva.adapter.mapper.MedicoMapper;
import br.com.universus.gerenciador_reserva.adapter.mapper.ReservaMapper;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;
import br.com.universus.gerenciador_reserva.infra.persistence.gateways.ReservaRepositoryJPAImpl;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.MedicoRepositoryJPA;
import br.com.universus.gerenciador_reserva.infra.persistence.repository.ReservaRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedico;
import static br.com.universus.gerenciador_reserva.utils.MedicoHelper.gerarMedicoEntity;
import static br.com.universus.gerenciador_reserva.utils.ReservaHelper.gerarReserva;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@Import({ReservaRepositoryJPAImpl.class, ReservaMapper.class, MedicoMapper.class})
class ReservaRepositoryJPAImplIT {

    @Autowired
    private ReservaRepositoryJPAImpl reservaRepository;

    @Autowired
    private ReservaRepositoryJPA reservaJPA;

    @Autowired
    private MedicoRepositoryJPA medicoRepository;

    private Medico medico;
    private MedicoEntity medicoEntity;
    private Reserva reserva;

    @BeforeEach
    void setup() {
        medico = gerarMedico();
        medicoEntity = gerarMedicoEntity(medico);
        medicoEntity = medicoRepository.save(medicoEntity);

        reserva = gerarReserva();
    }

    @Test
    void deveSalvarEBuscarReserva() {

        Reserva salva = reservaRepository.cadastrarReserva(reserva);

        Optional<Reserva> buscada = reservaRepository.buscarPorId(salva.getId());

        assertTrue(buscada.isPresent());
        assertEquals(salva.getId(), buscada.get().getId());
    }

//    @Test
//    void deveBuscarReservasPorMedicoEData() {
//        reservaRepository.cadastrarReserva(reserva);
//
//        List<LocalDateTime> horarios = reservaRepository
//                .buscaReservasPorMedicoEData(medico, reserva.getDataReserva().toLocalDate());
//
//        assertFalse(horarios.isEmpty());
//        assertTrue(horarios.contains(reserva.getDataReserva()));
//    }

    @Test
    void deveBuscarReservaExistente() {
        reservaRepository.cadastrarReserva(reserva);

        Optional<Reserva> existente = reservaRepository
                .buscarReservaExistente(medico, reserva.getDataReserva());

        assertTrue(existente.isPresent());
        assertEquals(reserva.getDataReserva(), existente.get().getDataReserva());
    }

    @Test
    void deveDeletarReservaPorId() {
        Reserva reserva = reservaRepository.cadastrarReserva(gerarReserva());
        reservaRepository.deletarPorId(reserva.getId());

        assertTrue(reservaJPA.findById(reserva.getId()).isEmpty());
    }
}

