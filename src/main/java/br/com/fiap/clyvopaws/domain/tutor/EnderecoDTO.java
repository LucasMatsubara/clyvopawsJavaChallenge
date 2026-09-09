package br.com.fiap.clyvopaws.domain.tutor;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
        @Size(max = 255, message = "A rua deve ter no máximo 255 caracteres")
        String rua,

        @Size(max = 20, message = "O número deve ter no máximo 20 caracteres")
        String numero,

        @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
        String complemento,

        @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
        String bairro,

        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido. Use o formato 00000-000")
        String cep,

        @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
        String cidade,

        @Pattern(regexp = "[A-Za-z]{2}", message = "Estado deve ser a sigla de 2 letras (ex: SP)")
        String estado
) {}