package br.com.fiap.clyvopaws.domain.tutor;

import br.com.fiap.clyvopaws.domain.pet.PetResumoDTO;

import java.util.List;

public record TutorResponseDTO(
        Long id,
        String nomeCompleto,
        String cpf,
        String email,
        String telefone,
        String fotoUrl
) {
    public TutorResponseDTO(Tutor tutor) {
        this(tutor.getId(), tutor.getNomeCompleto(), tutor.getCpf(), tutor.getEmail(), tutor.getTelefone(), tutor.getFotoUrl());
    }
}