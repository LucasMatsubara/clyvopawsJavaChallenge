package br.com.fiap.clyvovet.dto;

import br.com.fiap.clyvovet.enums.Especie;
import br.com.fiap.clyvovet.enums.FaseVida;
import br.com.fiap.clyvovet.enums.Porte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PetRequestDTO {

    @NotBlank(message = "O nome do pet é obrigatório")
    private String nome;

    @NotBlank(message = "A raça do pet é obrigatória")
    private String raca;

    @NotNull(message = "A espécie é obrigatória")
    private Especie especie;

    @NotNull(message = "A fase da vida é obrigatória")
    private FaseVida faseVida;

    @NotNull(message = "O porte é obrigatório")
    private Porte porte;

    private String descricao;

    private String fotoUrl;

    @NotNull(message = "A data de nascimento é obrigatória")
    @PastOrPresent(message = "A data de nascimento não pode estar no futuro")
    private LocalDate dataNascimento;

    @NotNull(message = "O ID do Tutor responsável é obrigatório")
    private Long tutorId;
}