package br.com.fiap.clyvopaws.domain.catalogo;

import br.com.fiap.clyvopaws.domain.pet.Especie;

public record CatalogoPreventivoResponseDTO(
        Long id,
        Especie especie,
        String raca,
        String doencaPredisposta,
        Integer idadeAlertaMeses,
        String dicaPrevencao,
        String cuidadosRecomendados
) {}