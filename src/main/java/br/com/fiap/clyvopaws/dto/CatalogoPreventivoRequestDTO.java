package br.com.fiap.clyvopaws.dto;

import br.com.fiap.clyvopaws.enums.Especie;
import jakarta.validation.constraints.*;

public record CatalogoPreventivoRequestDTO(
        @NotNull Especie especie, @NotBlank String raca, @NotBlank String doencaPredisposta,
        Integer idadeAlertaMeses, String dicaPrevencao, String cuidadosRecomendados
) {}
