package br.com.fiap.clyvopaws.domain.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record AgendamentoRequestDTO(

        @NotNull(message = "A data e hora do agendamento são obrigatórias")
        @Future(message = "A data e hora do agendamento devem estar no futuro")
        LocalDateTime dataHora,

        @NotBlank(message = "O título do agendamento é obrigatório")
        @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
        String titulo,

        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
        String descricao,

        @NotNull(message = "O ID da consulta de origem é obrigatório")
        Long consultaId
) {}
