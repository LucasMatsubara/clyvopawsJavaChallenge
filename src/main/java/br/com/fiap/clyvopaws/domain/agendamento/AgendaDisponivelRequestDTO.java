package br.com.fiap.clyvopaws.domain.agendamento;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AgendaDisponivelRequestDTO(
        @NotNull Long veterinarioId,
        @NotNull LocalDateTime dataHoraInicio,
        @NotNull LocalDateTime dataHoraFim
) {}