package br.com.fiap.clyvopaws.domain.tutor;

import br.com.fiap.clyvopaws.domain.pet.PetResumoDTO;

import java.util.List;

public record TutorResponseDTO(
        Long id,
        String nomeCompleto,
        String email,
        String telefone,
        String fotoUrl,
        EnderecoDTO endereco,
        List<PetResumoDTO> pets
) {}