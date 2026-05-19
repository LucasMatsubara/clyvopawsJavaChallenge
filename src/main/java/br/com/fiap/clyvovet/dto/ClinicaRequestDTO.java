package br.com.fiap.clyvovet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClinicaRequestDTO {

    @NotBlank(message = "O nome da unidade é obrigatório")
    private String nomeUnidade;

    @NotBlank(message = "A rede/franquia da clínica é obrigatória")
    private String redeFranquia;

    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos numéricos")
    private String cnpj;

    @NotBlank(message = "A cidade da unidade é obrigatória")
    private String cidade;
}