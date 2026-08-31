package br.com.fiap.clyvopaws.domain.medicamento;

import br.com.fiap.clyvopaws.domain.consulta.ConsultaResponseDTO;

import java.time.LocalDate;

public record MedicamentoResponseDTO(
        Long id, String nome, String dosagem, String frequencia,
        LocalDate dataInicio, Integer duracaoDias, StatusMedicamento status,
        ConsultaResponseDTO consulta
) {}