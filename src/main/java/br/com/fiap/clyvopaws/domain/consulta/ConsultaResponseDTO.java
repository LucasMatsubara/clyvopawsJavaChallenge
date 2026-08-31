package br.com.fiap.clyvopaws.domain.consulta;

import br.com.fiap.clyvopaws.domain.pet.PetResponseDTO;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id, LocalDateTime dataConsulta, String clinica,
        String nomeVeterinario, String laudo,
        PetResponseDTO pet
) {}