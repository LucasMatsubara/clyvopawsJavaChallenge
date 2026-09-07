package br.com.fiap.clyvopaws.domain.agendamento;

import java.time.LocalDateTime;

public record AgendaDisponivelResponseDTO(
        Long id,
        Long veterinarioId,
        String nomeVeterinario,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        Boolean disponivel
) {
    public AgendaDisponivelResponseDTO(AgendaDisponivel agenda) {
        this(
                agenda.getId(),
                agenda.getVeterinario() != null ? agenda.getVeterinario().getId() : null,
                agenda.getVeterinario() != null ? agenda.getVeterinario().getNomeCompleto() : null,
                agenda.getDataHoraInicio(),
                agenda.getDataHoraFim(),
                agenda.getDisponivel()
        );
    }
}