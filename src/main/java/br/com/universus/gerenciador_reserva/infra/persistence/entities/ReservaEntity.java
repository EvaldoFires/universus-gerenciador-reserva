package br.com.universus.gerenciador_reserva.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="Reserva")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePaciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "crm_medico", referencedColumnName = "crm", nullable = false)
    private MedicoEntity medico;
    private LocalDateTime dataReserva;

}
