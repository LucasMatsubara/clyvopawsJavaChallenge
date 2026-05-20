package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.ConsultaRequestDTO;
import br.com.fiap.clyvopaws.dto.ConsultaResponseDTO;
import br.com.fiap.clyvopaws.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> cadastrar(@Valid @RequestBody ConsultaRequestDTO request) {
        ConsultaResponseDTO response = consultaService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<ConsultaResponseDTO>> listarHistorico(@PathVariable Long petId) {
        return ResponseEntity.ok(consultaService.listarHistoricoPorPet(petId));
    }
}
