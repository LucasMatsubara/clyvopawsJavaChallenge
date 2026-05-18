package br.com.fiap.clyvovet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorResponseDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String endereco;
}