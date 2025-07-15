package br.com.universus.gerenciador_reserva.adapter.controller;

import br.com.universus.gerenciador_reserva.adapter.dto.ReservaDTO;
import br.com.universus.gerenciador_reserva.adapter.mapper.ReservaMapper;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.BuscarReservaUsecase;
import br.com.universus.gerenciador_reserva.application.usecases.reserva.CriarReservaUsecase;
import br.com.universus.gerenciador_reserva.domain.models.Reserva;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@AllArgsConstructor
public class ReservaController {

    private final BuscarReservaUsecase buscarReserva;
    private final CriarReservaUsecase criarReserva;
    private final ReservaMapper mapper;

    @GetMapping("/proximas-datas-disponiveis")
    public ResponseEntity<List<List<LocalDateTime>>> buscarProximosHorariosDisponiveis(
            @RequestParam String nomeMedico,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial){

        return ResponseEntity.ok(buscarReserva
                .buscarProximosHorariosDisponiveis(nomeMedico, dataInicial));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> criarReserva (@RequestBody ReservaDTO reservaDTO){
        Reserva reserva = mapper.toDomain(reservaDTO);
        reserva = criarReserva.cadastrarReserva(reserva);
        ReservaDTO reservaCadastrado = mapper.toDTO(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCadastrado);
    }
    
}
