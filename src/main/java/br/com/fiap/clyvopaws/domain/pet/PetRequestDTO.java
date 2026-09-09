package br.com.fiap.clyvopaws.domain.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record PetRequestDTO(
        @NotBlank(message = "O nome do pet é obrigatório")
        String nome,

        @NotNull(message = "A espécie é obrigatória")
        Especie especie,

        @NotBlank(message = "A raça é obrigatória")
        String raca,

        @Positive(message = "O peso deve ser maior que zero")
        Double peso,

        Sexo sexo,

        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento,

        String descricao,
        String fotoUrl,

        @NotNull(message = "O ID do tutor é obrigatório")
        Long tutorId
) {}