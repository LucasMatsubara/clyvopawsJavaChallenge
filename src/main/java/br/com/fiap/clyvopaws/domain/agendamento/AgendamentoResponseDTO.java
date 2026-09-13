package br.com.fiap.clyvopaws.domain.agendamento;

import br.com.fiap.clyvopaws.domain.consulta.ConsultaResponseDTO;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        String titulo,
        String descricao,
        Long petId,
        ConsultaResponseDTO consulta
) {
    public AgendamentoResponseDTO(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getTitulo(),
                agendamento.getDescricao(),

                (agendamento.getConsulta() != null && agendamento.getConsulta().getPet() != null)
                        ? agendamento.getConsulta().getPet().getId()
                        : null,

                agendamento.getConsulta() != null ? new ConsultaResponseDTO(agendamento.getConsulta()) : null
        );
    }

}