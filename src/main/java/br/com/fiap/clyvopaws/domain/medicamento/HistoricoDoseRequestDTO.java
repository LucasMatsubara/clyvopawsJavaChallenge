package br.com.fiap.clyvopaws.domain.medicamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public record HistoricoDoseRequestDTO(
        @NotNull(message = "A data e hora são obrigatórias")
        @PastOrPresent(message = "A data e hora da dose não podem estar no futuro")
        LocalDateTime dataHoraToma,

        @NotNull(message = "O ID do medicamento é obrigatório")
        Long medicamentoId
) {}