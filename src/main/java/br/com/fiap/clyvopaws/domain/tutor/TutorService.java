package br.com.fiap.clyvopaws.domain.tutor;

import br.com.fiap.clyvopaws.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TutorResponseDTO cadastrar(TutorRequestDTO request) {
        if (tutorRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        if (tutorRepository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        var user = new User();
        user.setUsername(request.email());
        user.setPassword(passwordEncoder.encode(request.senha()));
        user.setRole("TUTOR");

        Tutor tutor = new Tutor();
        tutor.setNomeCompleto(request.nomeCompleto());
        tutor.setCpf(request.cpf());
        tutor.setTelefone(request.telefone());
        tutor.setEmail(request.email());
        tutor.setFotoUrl(request.fotoUrl());
        tutor.setUser(user);

        if (request.endereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setRua(request.endereco().rua());
            endereco.setNumero(request.endereco().numero());
            endereco.setComplemento(request.endereco().complemento());
            endereco.setBairro(request.endereco().bairro());
            endereco.setCep(request.endereco().cep());
            endereco.setCidade(request.endereco().cidade());
            endereco.setEstado(request.endereco().estado());
            tutor.setEndereco(endereco);
        }

        return toResponseDTO(tutorRepository.save(tutor));
    }

    @Transactional(readOnly = true)
    public TutorResponseDTO buscarPorId(Long id) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado."));
        return toResponseDTO(tutor);
    }

    @Transactional(readOnly = true)
    public Page<TutorResponseDTO> listarTodos(Pageable pageable) {
        return tutorRepository.findAll(pageable).map(this::toResponseDTO);
    }

    @Transactional
    public TutorResponseDTO atualizar(Long id, TutorRequestDTO request) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado."));

        tutor.setNomeCompleto(request.nomeCompleto());
        tutor.setCpf(request.cpf());
        tutor.setTelefone(request.telefone());
        tutor.setEmail(request.email());
        tutor.setFotoUrl(request.fotoUrl());

        if (request.endereco() != null) {
            if (tutor.getEndereco() == null) {
                tutor.setEndereco(new Endereco());
            }
            tutor.getEndereco().setRua(request.endereco().rua());
            tutor.getEndereco().setNumero(request.endereco().numero());
            tutor.getEndereco().setComplemento(request.endereco().complemento());
            tutor.getEndereco().setBairro(request.endereco().bairro());
            tutor.getEndereco().setCep(request.endereco().cep());
            tutor.getEndereco().setCidade(request.endereco().cidade());
            tutor.getEndereco().setEstado(request.endereco().estado());
        }

        return toResponseDTO(tutorRepository.save(tutor));
    }

    @Transactional
    public void excluir(Long id) {
        if (!tutorRepository.existsById(id)) {
            throw new EntityNotFoundException("Tutor não encontrado.");
        }
        tutorRepository.deleteById(id);
    }

    private TutorResponseDTO toResponseDTO(Tutor tutor) {
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getNomeCompleto(),
                tutor.getCpf(),
                tutor.getTelefone(),
                tutor.getEmail(),
                tutor.getFotoUrl()
        );
    }
}