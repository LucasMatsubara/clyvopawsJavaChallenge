package br.com.fiap.clyvovet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TutorRequestDTO {

    @NotBlank(message = "O nome do tutor é obrigatório")
    @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres")
    private String nome;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail informado deve ser válido")
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos, sem pontos ou traços")
    private String cpf;

    @NotBlank(message = "O endereço completo é obrigatório")
    private String endereco;
}