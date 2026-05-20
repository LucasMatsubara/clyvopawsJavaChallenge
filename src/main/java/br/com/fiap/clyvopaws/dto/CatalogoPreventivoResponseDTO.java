package br.com.fiap.clyvopaws.dto;

public record CatalogoPreventivoResponseDTO(
        Long id,
        String especie,
        String raca,
        String doencaPredisposta,
        Integer idadeAlertaMeses,
        String dicaPrevencao,
        String cuidadosRecomendados
) {}