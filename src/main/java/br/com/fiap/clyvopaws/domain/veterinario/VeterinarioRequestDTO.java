package br.com.fiap.clyvopaws.domain.veterinario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VeterinarioRequestDTO(
        @NotBlank(message = "O nome completo é obrigatório")
        @Size(max = 100, message = "O nome completo deve ter no máximo 100 caracteres")
        String nomeCompleto,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
        String email,

        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
        String telefone,

        @Size(max = 255, message = "A URL da foto deve ter no máximo 255 caracteres")
        String fotoUrl,

        @NotBlank(message = "O CRMV é obrigatório")
        @Size(max = 20, message = "O CRMV deve ter no máximo 20 caracteres")
        String crmv,

        @NotNull(message = "O ID da clínica é obrigatório")
        Long clinicaId,

        @NotBlank(message = "O username é obrigatório")
        @Size(max = 255, message = "O username deve ter no máximo 255 caracteres")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 72, message = "A senha deve ter entre 6 e 72 caracteres")
        String password
) {}