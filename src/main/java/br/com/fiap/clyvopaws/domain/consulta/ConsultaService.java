package br.com.fiap.clyvopaws.domain.consulta;

import br.com.fiap.clyvopaws.auth.AuthorizationService;
import br.com.fiap.clyvopaws.domain.clinica.ClinicaRepository;
import br.com.fiap.clyvopaws.domain.pet.Pet;
import br.com.fiap.clyvopaws.domain.pet.PetRepository;
import br.com.fiap.clyvopaws.domain.veterinario.VeterinarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PetRepository petRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final ClinicaRepository clinicaRepository;
    private final AuthorizationService authorizationService;

    private void assertPodeVerConsulta(Consulta consulta) {
        if (authorizationService.isAdmin()) return;
        if (authorizationService.hasRole("VETERINARIO")) {
            authorizationService.assertSelfVeterinario(consulta.getVeterinario().getId());
            return;
        }
        authorizationService.assertSelfTutor(consulta.getPet().getTutor().getId());
    }

    @Transactional
    public ConsultaResponseDTO cadastrar(ConsultaRequestDTO dto) {
        authorizationService.assertSelfVeterinario(dto.veterinarioId());
        var pet = petRepository.findById(dto.petId())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado."));
        var veterinario = veterinarioRepository.findById(dto.veterinarioId())
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));
        var clinica = clinicaRepository.findById(dto.clinicaId())
                .orElseThrow(() -> new EntityNotFoundException("Clínica não encontrada."));

        var consulta = new Consulta();
        consulta.setPet(pet);
        consulta.setVeterinario(veterinario);
        consulta.setClinica(clinica);
        consulta.setDataHora(dto.dataHora());
        consulta.setResumo(dto.resumo());
        consulta.setDiagnostico(dto.diagnostico());

        return new ConsultaResponseDTO(consultaRepository.save(consulta));
    }

    @Transactional(readOnly = true)
    public ConsultaResponseDTO buscarPorId(Long id) {
        var consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        assertPodeVerConsulta(consulta);
        return new ConsultaResponseDTO(consulta);
    }

    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarTodas(Pageable pageable) {
        if (authorizationService.isAdmin()) {
            return consultaRepository.findAll(pageable).map(ConsultaResponseDTO::new);
        }
        var vet = authorizationService.currentVeterinarioOrNull();
        if (vet == null) {
            throw new AccessDeniedException("Apenas veterinários (das próprias consultas) ou administradores podem listar consultas.");
        }
        return consultaRepository.findByVeterinarioId(vet.getId(), pageable).map(ConsultaResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarHistoricoPorPet(Long petId, Pageable pageable) {
        if (authorizationService.isAdmin()) {
            return consultaRepository.findByPetId(petId, pageable).map(ConsultaResponseDTO::new);
        }
        if (authorizationService.hasRole("VETERINARIO")) {
            var vet = authorizationService.currentVeterinarioOrNull();
            Long vetId = vet != null ? vet.getId() : -1L;
            return consultaRepository.findByPetIdAndVeterinarioId(petId, vetId, pageable).map(ConsultaResponseDTO::new);
        }
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado."));
        authorizationService.assertSelfTutor(pet.getTutor().getId());
        return consultaRepository.findByPetId(petId, pageable).map(ConsultaResponseDTO::new);
    }

    @Transactional
    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {
        var consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        authorizationService.assertSelfVeterinario(consulta.getVeterinario().getId());

        var pet = petRepository.findById(dto.petId())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado."));
        var veterinario = veterinarioRepository.findById(dto.veterinarioId())
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));
        var clinica = clinicaRepository.findById(dto.clinicaId())
                .orElseThrow(() -> new EntityNotFoundException("Clínica não encontrada."));

        consulta.setPet(pet);
        consulta.setVeterinario(veterinario);
        consulta.setClinica(clinica);
        consulta.setDataHora(dto.dataHora());
        consulta.setResumo(dto.resumo());
        consulta.setDiagnostico(dto.diagnostico());

        return new ConsultaResponseDTO(consultaRepository.save(consulta));
    }

    @Transactional
    public void excluir(Long id) {
        var consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        authorizationService.assertSelfVeterinario(consulta.getVeterinario().getId());
        consultaRepository.deleteById(id);
    }
}