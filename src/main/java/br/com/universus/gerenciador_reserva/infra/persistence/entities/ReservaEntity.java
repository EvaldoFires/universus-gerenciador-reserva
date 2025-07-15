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
    private String nomeMedico;
    private LocalDateTime dataReserva;


}
