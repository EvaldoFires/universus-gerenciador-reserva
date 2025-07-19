package br.com.universus.gerenciador_reserva.utils;

import br.com.universus.gerenciador_reserva.adapter.dto.medico.MedicoDTO;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import br.com.universus.gerenciador_reserva.infra.persistence.entities.MedicoEntity;

public class MedicoHelper {

    public static Medico gerarMedico(){
        return new Medico("12345-6/SP",
                "Dr. João Pedro Silva");
    }

    public static MedicoDTO gerarMedicoDto (Medico medico){
        return new MedicoDTO(medico.getCrm(),
                medico.getNome());
    }

    public static MedicoEntity gerarMedicoEntity (Medico medico){
        return new MedicoEntity(medico.getCrm(),
                medico.getNome());
    }


}
