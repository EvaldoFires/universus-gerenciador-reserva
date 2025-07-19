package br.com.universus.gerenciador_reserva.application.gateways;


import br.com.universus.gerenciador_reserva.domain.models.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository {
    
    Medico cadastrarMedico(Medico medico);
    
    Optional<Medico> buscarPorCRM(String crm);
    
    List<Medico> listarTodos();
    
    void deletarPorCRM(String crm);
}
