package br.com.universus.gerenciador_reserva.infra.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Medico")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicoEntity {
    @Id
    private String crm;
    private String nome;
}
