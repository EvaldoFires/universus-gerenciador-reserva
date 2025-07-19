package br.com.universus.gerenciador_reserva.infra.persistence.repository;

import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepositoryJPA extends JpaRepository<MedicoEntity, String> {

}
