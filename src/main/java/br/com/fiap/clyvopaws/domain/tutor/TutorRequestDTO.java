package br.com.fiap.clyvopaws.domain.tutor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record TutorRequestDTO(
        @NotBlank(message = "O nome completo é obrigatório")
        @Size(max = 100, message = "O nome completo deve ter no máximo 100 caracteres")
        String nomeCompleto,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido. Use 11 dígitos (com ou sem pontuação)")
        String cpf,

        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
        String telefone,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 72, message = "A senha deve ter entre 6 e 72 caracteres")
        String senha,

        @Size(max = 255, message = "A URL da foto deve ter no máximo 255 caracteres")
        String fotoUrl,

        @Valid
        EnderecoDTO endereco
) {}