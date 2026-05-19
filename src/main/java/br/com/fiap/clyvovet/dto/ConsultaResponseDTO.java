package br.com.fiap.clyvovet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ConsultaResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String nomeUnidadeClinica;
    private String redeFranquia;
    private Double peso;
    private String queixaPrincipal;
    private String diagnostico;
    private String prescricao;
}