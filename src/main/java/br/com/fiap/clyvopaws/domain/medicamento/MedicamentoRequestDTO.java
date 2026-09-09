package br.com.fiap.clyvopaws.domain.medicamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record MedicamentoRequestDTO(
        @NotBlank(message = "O nome do medicamento é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "A dosagem é obrigatória")
        @Size(max = 100, message = "A dosagem deve ter no máximo 100 caracteres")
        String dosagem,

        @NotBlank(message = "A frequência é obrigatória")
        @Size(max = 100, message = "A frequência deve ter no máximo 100 caracteres")
        String frequencia,

        @NotNull(message = "A data de início é obrigatória")
        LocalDate dataInicio,

        @Positive(message = "A duração em dias deve ser maior que zero")
        Integer duracaoDias,

        @NotNull(message = "O status do medicamento é obrigatório")
        StatusMedicamento status,

        @NotNull(message = "O ID da consulta é obrigatório")
        Long consultaId
) {}