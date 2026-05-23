package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.TutorRequestDTO;
import br.com.fiap.clyvopaws.dto.TutorResponseDTO;
import br.com.fiap.clyvopaws.model.Tutor;
import br.com.fiap.clyvopaws.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    @Transactional
    public TutorResponseDTO cadastrar(TutorRequestDTO request) {
        if (tutorRepository.existsByEmail(request.email())) throw new IllegalArgumentException("E-mail já cadastrado.");
        if (tutorRepository.existsByCpf(request.cpf())) throw new IllegalArgumentException("CPF já cadastrado.");

        Tutor tutor = new Tutor();
        tutor.setNomeCompleto(request.nomeCompleto());
        tutor.setCpf(request.cpf());
        tutor.setTelefone(request.telefone());
        tutor.setEmail(request.email());
        tutor.setSenha(request.senha());
        tutor.setFotoUrl(request.fotoUrl());

        return toResponseDTO(tutorRepository.save(tutor));
    }

    public TutorResponseDTO buscarPorId(Long id) {
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado."));
        return toResponseDTO(tutor);
    }

    @Transactional
    public TutorResponseDTO atualizar(Long id, TutorRequestDTO request) {
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado."));
        tutor.setNomeCompleto(request.nomeCompleto());
        tutor.setTelefone(request.telefone());
        tutor.setFotoUrl(request.fotoUrl());
        return toResponseDTO(tutorRepository.save(tutor));
    }

    @Transactional
    public void excluir(Long id) {
        if (!tutorRepository.existsById(id)) throw new EntityNotFoundException("Tutor não encontrado.");
        tutorRepository.deleteById(id);
    }

    private TutorResponseDTO toResponseDTO(Tutor tutor) {
        return new TutorResponseDTO(tutor.getId(), tutor.getNomeCompleto(), tutor.getEmail(), tutor.getTelefone(), tutor.getFotoUrl());
    }
}