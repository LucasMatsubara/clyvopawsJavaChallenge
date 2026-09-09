package br.com.fiap.clyvopaws.domain.catalogo;

import br.com.fiap.clyvopaws.domain.pet.Especie;
import jakarta.validation.constraints.*;

public record CatalogoPreventivoRequestDTO(
        @NotNull Especie especie, @NotBlank String raca, @NotBlank String doencaPredisposta,
        @PositiveOrZero Integer idadeAlertaMeses, String dicaPrevencao, String cuidadosRecomendados
) {}
