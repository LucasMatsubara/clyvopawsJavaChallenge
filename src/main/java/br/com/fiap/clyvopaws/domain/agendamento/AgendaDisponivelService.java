package br.com.fiap.clyvopaws.domain.agendamento;

import br.com.fiap.clyvopaws.auth.AuthorizationService;
import br.com.fiap.clyvopaws.domain.veterinario.VeterinarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaDisponivelService {

    private final AgendaDisponivelRepository repository;
    private final VeterinarioRepository veterinarioRepository;
    private final AuthorizationService authorizationService;

    @Transactional
    public AgendaDisponivelResponseDTO cadastrar(AgendaDisponivelRequestDTO dto) {
        authorizationService.assertSelfVeterinario(dto.veterinarioId());
        var veterinario = veterinarioRepository.findById(dto.veterinarioId())
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));

        var agenda = new AgendaDisponivel();
        agenda.setVeterinario(veterinario);
        agenda.setDataHoraInicio(dto.dataHoraInicio());
        agenda.setDataHoraFim(dto.dataHoraFim());
        agenda.setDisponivel(true);

        return new AgendaDisponivelResponseDTO(repository.save(agenda));
    }

    @Transactional(readOnly = true)
    public List<AgendaDisponivelResponseDTO> listarDisponiveisPorVeterinario(Long veterinarioId) {
        return repository.findByVeterinarioIdAndDisponivelTrue(veterinarioId)
                .stream()
                .map(AgendaDisponivelResponseDTO::new)
                .toList();
    }

    @Transactional
    public void excluir(Long id) {
        var agenda = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agenda não encontrada."));
        authorizationService.assertSelfVeterinario(agenda.getVeterinario().getId());
        repository.deleteById(id);
    }
}