package br.com.fiap.clyvopaws.domain.tutor;

import jakarta.validation.constraints.NotBlank;

public record TutorUpdateDTO(
        @NotBlank(message = "O nome completo é obrigatório")
        String nomeCompleto,

        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        String cpf,
        String telefone,
        String foto
) {
}