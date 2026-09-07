package br.com.fiap.clyvopaws.domain.clinica;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;

    @Transactional
    public ClinicaResponseDTO cadastrar(ClinicaRequestDTO dto) {
        var clinica = new Clinica();
        clinica.setNome(dto.nome());
        clinica.setCnpj(dto.cnpj());
        clinica.setTelefone(dto.telefone());
        clinica.setEmail(dto.email());
        return new ClinicaResponseDTO(clinicaRepository.save(clinica));
    }

    @Transactional(readOnly = true)
    public Clinica buscarPorId(Long id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clínica não encontrada."));
    }

    @Transactional(readOnly = true)
    public ClinicaResponseDTO buscarDtoPorId(Long id) {
        return new ClinicaResponseDTO(buscarPorId(id));
    }

    @Transactional(readOnly = true)
    public Page<ClinicaResponseDTO> listarTodas(Pageable pageable) {
        return clinicaRepository.findAll(pageable).map(ClinicaResponseDTO::new);
    }

    @Transactional
    public ClinicaResponseDTO atualizar(Long id, ClinicaRequestDTO dto) {
        var clinica = buscarPorId(id);
        clinica.setNome(dto.nome());
        clinica.setCnpj(dto.cnpj());
        clinica.setTelefone(dto.telefone());
        clinica.setEmail(dto.email());
        return new ClinicaResponseDTO(clinicaRepository.save(clinica));
    }

    @Transactional
    public void excluir(Long id) {
        if (!clinicaRepository.existsById(id)) {
            throw new EntityNotFoundException("Clínica não encontrada.");
        }
        clinicaRepository.deleteById(id);
    }
}