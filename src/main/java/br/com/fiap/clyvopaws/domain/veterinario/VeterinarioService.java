package br.com.fiap.clyvopaws.domain.veterinario;

import br.com.fiap.clyvopaws.domain.clinica.ClinicaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final ClinicaService clinicaService;

    @Transactional(readOnly = true)
    public Veterinario buscarPorId(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));
    }

    // Futuros métodos de cadastro (recebendo DTO, validando Clínica e encriptando senha do User) virão aqui
}