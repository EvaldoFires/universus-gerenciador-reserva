package br.com.universus.gerenciador_reserva.adapter.controller;

import br.com.universus.gerenciador_reserva.adapter.dto.medico.MedicoDTO;
import br.com.universus.gerenciador_reserva.adapter.mapper.MedicoMapper;
import br.com.universus.gerenciador_reserva.application.usecases.medico.BuscarMedicoUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.medico.CriarMedicoUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.medico.DeletarMedicoUsecase;
import br.com.universus.gerenciador_reserva.domain.models.Medico;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@AllArgsConstructor
public class MedicoController {

    private final BuscarMedicoUsecase buscarMedico;
    private final CriarMedicoUsecase criarMedico;
    private final DeletarMedicoUsecase deletarMedico;
    private final MedicoMapper mapper;

    @GetMapping("/medico")
    public ResponseEntity<MedicoDTO> buscarPorCRM(@RequestParam String crm){
        MedicoDTO medicoDTO = mapper.toDTO(buscarMedico.buscarPorCPM(crm));
        return ResponseEntity.ok(medicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarTodos(){
        List<MedicoDTO> medicos = buscarMedico.listarTodos().stream().map(mapper::toDTO).toList();
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> criarMedico (@RequestBody MedicoDTO medicoDTO){
        Medico medico = mapper.toDomain(medicoDTO);
        medico = criarMedico.cadastrarMedico(medico);
        MedicoDTO medicoCadastrado = mapper.toDTO(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCadastrado);
    }

    @DeleteMapping("/medico")
    public ResponseEntity<Void> deletarMedicoPorCRM(@RequestParam String crm){
        deletarMedico.deletarPorCPF(crm);
        return ResponseEntity.noContent().build();
    }
}
