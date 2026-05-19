package br.com.fiap.clyvovet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClinicaResponseDTO {
    private Long id;
    private String nomeUnidade;
    private String redeFranquia;
    private String cnpj;
    private String city; // Ajustado conforme o campo mapeado na sua entidade
}