package br.com.fiap.clyvopaws.domain.clinica;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;

    @Transactional(readOnly = true)
    public Clinica buscarPorId(Long id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clínica não encontrada."));
    }

    // Futuros métodos de salvar/atualizar Clínica virão aqui
}