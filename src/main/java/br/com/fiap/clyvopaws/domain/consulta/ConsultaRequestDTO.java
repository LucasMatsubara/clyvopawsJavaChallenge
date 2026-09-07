package br.com.fiap.clyvopaws.domain.consulta;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        @NotNull Long petId,
        @NotNull Long veterinarioId,
        @NotNull Long clinicaId,
        @NotNull LocalDateTime dataHora,
        String resumo,
        String diagnostico
) {}