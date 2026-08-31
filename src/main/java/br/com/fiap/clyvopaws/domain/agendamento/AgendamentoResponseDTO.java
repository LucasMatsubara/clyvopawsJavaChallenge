package br.com.fiap.clyvopaws.domain.agendamento;

import br.com.fiap.clyvopaws.domain.consulta.ConsultaResponseDTO;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id, LocalDateTime dataHora, String titulo, String descricao,
        ConsultaResponseDTO consulta
) {}