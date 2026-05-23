package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.CatalogoPreventivoRequestDTO;
import br.com.fiap.clyvopaws.dto.CatalogoPreventivoResponseDTO;
import br.com.fiap.clyvopaws.enums.Especie;
import br.com.fiap.clyvopaws.service.CatalogoPreventivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/planos-preventivos")
@RequiredArgsConstructor
public class CatalogoPreventivoController {
    private final CatalogoPreventivoService catalogoPreventivoService;

    @GetMapping
    public ResponseEntity<List<CatalogoPreventivoResponseDTO>> buscarPlanoPreventivo(
            @RequestParam("especie") Especie especie,
            @RequestParam("raca") String raca) {
        return ResponseEntity.ok(catalogoPreventivoService.buscarPlanoPreventivo(especie, raca));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoPreventivoResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(catalogoPreventivoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CatalogoPreventivoResponseDTO> cadastrar(@Valid @RequestBody CatalogoPreventivoRequestDTO request) {
        CatalogoPreventivoResponseDTO response = catalogoPreventivoService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoPreventivoResponseDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody CatalogoPreventivoRequestDTO request) {
        return ResponseEntity.ok(catalogoPreventivoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        catalogoPreventivoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}