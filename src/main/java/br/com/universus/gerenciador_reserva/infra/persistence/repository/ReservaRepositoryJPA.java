package br.com.universus.gerenciador_reserva.infra.persistence.repository;

import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepositoryJPA extends JpaRepository<ReservaEntity, Long> {

    @Query("SELECT r.dataReserva FROM ReservaEntity r " +
            "WHERE r.medico = :medico " +
            "AND FUNCTION ('DATE', r.dataReserva) >= :dataReserva")
    List<LocalDateTime> findAllByMedicoAndDataReserva(MedicoEntity medico, LocalDate dataReserva);

    Optional<ReservaEntity> findByMedicoAndDataReserva(MedicoEntity medico, LocalDateTime dataReserva);
}
