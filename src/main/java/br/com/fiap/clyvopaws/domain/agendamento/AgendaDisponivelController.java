package br.com.fiap.clyvopaws.domain.agendamento;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("/agendas")
@RequiredArgsConstructor
public class AgendaDisponivelController {

    private final AgendaDisponivelService service;

    @PostMapping
    public ResponseEntity<AgendaDisponivelResponseDTO> cadastrar(@RequestBody @Valid AgendaDisponivelRequestDTO dto) {
        var response = service.cadastrar(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<AgendaDisponivelResponseDTO>> listarDisponiveis(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(service.listarDisponiveisPorVeterinario(veterinarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}