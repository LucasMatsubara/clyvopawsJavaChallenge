package br.com.fiap.clyvopaws.domain.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PetRequestDTO(
        @NotBlank(message = "O nome do pet é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "A espécie é obrigatória")
        Especie especie,

        @NotBlank(message = "A raça é obrigatória")
        @Size(max = 50, message = "A raça deve ter no máximo 50 caracteres")
        String raca,

        @Positive(message = "O peso deve ser maior que zero")
        Double peso,

        Sexo sexo,

        @NotNull(message = "A data de nascimento é obrigatória")
        @PastOrPresent(message = "A data de nascimento não pode estar no futuro")
        LocalDate dataNascimento,

        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @Size(max = 255, message = "A URL da foto deve ter no máximo 255 caracteres")
        String fotoUrl,

        @NotNull(message = "O ID do tutor é obrigatório")
        Long tutorId
) {}