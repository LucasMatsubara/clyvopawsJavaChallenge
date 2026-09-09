package br.com.fiap.clyvopaws.domain.catalogo;

import br.com.fiap.clyvopaws.domain.pet.Especie;
import jakarta.validation.constraints.*;

public record CatalogoPreventivoRequestDTO(
        @NotNull(message = "A espécie é obrigatória")
        Especie especie,

        @NotBlank(message = "A raça é obrigatória")
        @Size(max = 50, message = "A raça deve ter no máximo 50 caracteres")
        String raca,

        @NotBlank(message = "A doença predisposta é obrigatória")
        @Size(max = 150, message = "A doença predisposta deve ter no máximo 150 caracteres")
        String doencaPredisposta,

        @PositiveOrZero(message = "A idade de alerta não pode ser negativa")
        Integer idadeAlertaMeses,

        @Size(max = 4000, message = "A dica de prevenção deve ter no máximo 4000 caracteres")
        String dicaPrevencao,

        @Size(max = 4000, message = "Os cuidados recomendados devem ter no máximo 4000 caracteres")
        String cuidadosRecomendados
) {}
