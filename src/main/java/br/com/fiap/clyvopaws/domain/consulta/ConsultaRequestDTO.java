package br.com.fiap.clyvopaws.domain.consulta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        @NotNull(message = "O ID do pet é obrigatório")
        Long petId,

        @NotNull(message = "O ID do veterinário é obrigatório")
        Long veterinarioId,

        @NotNull(message = "O ID da clínica é obrigatório")
        Long clinicaId,

        @NotNull(message = "A data e hora da consulta são obrigatórias")
        LocalDateTime dataHora,

        @Size(max = 255, message = "O resumo deve ter no máximo 255 caracteres")
        String resumo,

        @Size(max = 255, message = "O diagnóstico deve ter no máximo 255 caracteres")
        String diagnostico
) {}