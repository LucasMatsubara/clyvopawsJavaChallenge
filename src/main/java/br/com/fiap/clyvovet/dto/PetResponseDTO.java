package br.com.fiap.clyvovet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetResponseDTO {
    private Long id;
    private String nome;
    private String raca;
    private Integer idadeMeses;
    private String fotoUrl;
}