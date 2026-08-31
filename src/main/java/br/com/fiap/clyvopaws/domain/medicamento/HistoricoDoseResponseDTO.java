package br.com.fiap.clyvopaws.domain.medicamento;

import java.time.LocalDateTime;

public record HistoricoDoseResponseDTO(
        Long id, LocalDateTime dataHoraToma,
        MedicamentoResponseDTO medicamento
) {}