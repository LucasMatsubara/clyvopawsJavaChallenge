package br.com.fiap.clyvopaws.domain.consulta;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id,
        String nomePet,
        String nomeVeterinario,
        String nomeClinica,
        LocalDateTime dataHora,
        String resumo,
        String diagnostico
) {
    public ConsultaResponseDTO(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getPet() != null ? consulta.getPet().getNome() : null,
                consulta.getVeterinario() != null ? consulta.getVeterinario().getNomeCompleto() : null,
                consulta.getClinica() != null ? consulta.getClinica().getNome() : null,
                consulta.getDataHora(),
                consulta.getResumo(),
                consulta.getDiagnostico()
        );
    }
}