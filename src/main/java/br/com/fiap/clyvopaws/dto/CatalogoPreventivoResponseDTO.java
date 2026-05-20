package br.com.fiap.clyvopaws.dto;

import br.com.fiap.clyvopaws.enums.Especie;

public record CatalogoPreventivoResponseDTO(
        Long id,
        Especie especie,
        String raca,
        String doencaPredisposta,
        Integer idadeAlertaMeses,
        String dicaPrevencao,
        String cuidadosRecomendados
) {}