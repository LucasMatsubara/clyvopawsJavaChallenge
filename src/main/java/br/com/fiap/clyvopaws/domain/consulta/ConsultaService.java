package br.com.fiap.clyvopaws.domain.consulta;

import br.com.fiap.clyvopaws.domain.clinica.ClinicaRepository;
import br.com.fiap.clyvopaws.domain.pet.PetRepository;
import br.com.fiap.clyvopaws.domain.veterinario.VeterinarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PetRepository petRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final ClinicaRepository clinicaRepository;

    @Transactional
    public ConsultaResponseDTO cadastrar(ConsultaRequestDTO dto) {
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
        return new ConsultaResponseDTO(consulta);
    }

    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarTodas(Pageable pageable) {
        return consultaRepository.findAll(pageable).map(ConsultaResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarHistoricoPorPet(Long petId, Pageable pageable) {
        return consultaRepository.findByPetId(petId, pageable).map(ConsultaResponseDTO::new);
    }

    @Transactional
    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {
        var consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));

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
        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("Consulta não encontrada.");
        }
        consultaRepository.deleteById(id);
    }
}