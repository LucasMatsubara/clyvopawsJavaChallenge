package br.com.fiap.clyvopaws.domain.veterinario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeterinarioRequestDTO(
        @NotBlank String nomeCompleto,
        @NotBlank @Email String email,
        String telefone,
        String fotoUrl,
        @NotBlank String crmv,
        @NotNull Long clinicaId,
        @NotBlank String username,
        @NotBlank String password
) {}