package br.com.fiap.clyvopaws.domain.clinica;

import jakarta.validation.constraints.NotBlank;

public record ClinicaRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        String cnpj,
        String telefone,
        String email
) {}