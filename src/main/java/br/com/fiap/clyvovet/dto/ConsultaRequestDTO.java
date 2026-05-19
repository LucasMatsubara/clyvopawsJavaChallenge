package br.com.fiap.clyvovet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsultaRequestDTO {

    private LocalDateTime dataHora;

    @NotNull(message = "O peso do pet na consulta é obrigatório")
    @Positive(message = "O peso deve ser um valor maior que zero")
    private Double pesoNaConsulta;

    @NotBlank(message = "A queixa principal ou sintomas do pet são obrigatórios")
    private String queixaPrincipal;

    private String diagnostico;

    private String prescricao;

    @NotNull(message = "O ID da clínica onde ocorreu o atendimento é obrigatório")
    private Long clinicaId;
}