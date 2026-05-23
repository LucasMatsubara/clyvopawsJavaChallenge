package br.com.fiap.clyvopaws.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AgendamentoRequestDTO(

        @NotNull(message = "A data e hora do agendamento são obrigatórias")
        LocalDateTime dataHora,

        @NotBlank(message = "O título do agendamento é obrigatório")
        String titulo,

        String descricao,

        @NotNull(message = "O ID da consulta de origem é obrigatório")
        Long consultaId
) {}
