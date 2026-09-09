package br.com.fiap.clyvopaws.domain.agendamento;

import br.com.fiap.clyvopaws.auth.AuthorizationService;
import br.com.fiap.clyvopaws.domain.consulta.Consulta;
import br.com.fiap.clyvopaws.domain.consulta.ConsultaRepository;
import br.com.fiap.clyvopaws.domain.consulta.ConsultaResponseDTO;
import br.com.fiap.clyvopaws.domain.consulta.ConsultaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final ConsultaRepository consultaRepository;
    private final ConsultaService consultaService;
    private final AgendaDisponivelRepository agendaDisponivelRepository;
    private final AuthorizationService authorizationService;

    private void assertPodeVer(Agendamento agendamento) {
        Consulta consulta = agendamento.getConsulta();
        if (authorizationService.isAdmin()) return;
        if (authorizationService.hasRole("VETERINARIO")) {
            authorizationService.assertSelfVeterinario(consulta.getVeterinario().getId());
            return;
        }
        authorizationService.assertSelfTutor(consulta.getPet().getTutor().getId());
    }

    @Transactional
    public AgendamentoResponseDTO cadastrar(AgendamentoRequestDTO request) {
        Consulta consulta = consultaRepository.findById(request.consultaId())
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        authorizationService.assertSelfTutor(consulta.getPet().getTutor().getId());

        var veterinario = consulta.getVeterinario();
        if (veterinario != null) {
            var agendaDisponivel = agendaDisponivelRepository
                    .findByVeterinarioIdAndDisponivelTrue(veterinario.getId())
                    .stream()
                    .filter(a -> !request.dataHora().isBefore(a.getDataHoraInicio()) && !request.dataHora().isAfter(a.getDataHoraFim()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("O veterinário não possui horário disponível para esta data/hora."));

            agendaDisponivel.setDisponivel(false);
            agendaDisponivelRepository.save(agendaDisponivel);
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(request.dataHora());
        agendamento.setTitulo(request.titulo());
        agendamento.setDescricao(request.descricao());
        agendamento.setConsulta(consulta);
        return toResponseDTO(agendamentoRepository.save(agendamento));
    }

    @Transactional(readOnly = true)
    public AgendamentoResponseDTO buscarPorId(Long id) {
        Agendamento ag = agendamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));
        assertPodeVer(ag);
        return toResponseDTO(ag);
    }

    @Transactional(readOnly = true)
    public Page<AgendamentoResponseDTO> listarTodos(Pageable pageable) {
        if (!authorizationService.isAdmin()) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "Apenas administradores podem listar todos os agendamentos.");
        }
        return agendamentoRepository.findAll(pageable).map(this::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public List<AgendamentoResponseDTO> listarPorConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        if (!authorizationService.isAdmin()) {
            if (authorizationService.hasRole("VETERINARIO")) {
                authorizationService.assertSelfVeterinario(consulta.getVeterinario().getId());
            } else {
                authorizationService.assertSelfTutor(consulta.getPet().getTutor().getId());
            }
        }
        return agendamentoRepository.findByConsultaId(consultaId).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO request) {
        Agendamento ag = agendamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));
        authorizationService.assertSelfTutor(ag.getConsulta().getPet().getTutor().getId());
        ag.setDataHora(request.dataHora());
        ag.setTitulo(request.titulo());
        ag.setDescricao(request.descricao());
        return toResponseDTO(agendamentoRepository.save(ag));
    }

    @Transactional
    public void excluir(Long id) {
        Agendamento ag = agendamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));
        authorizationService.assertSelfTutor(ag.getConsulta().getPet().getTutor().getId());
        agendamentoRepository.deleteById(id);
    }

    private AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        ConsultaResponseDTO consultaDTO = agendamento.getConsulta() != null
                ? new ConsultaResponseDTO(agendamento.getConsulta())
                : null;

        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getTitulo(),
                agendamento.getDescricao(),
                consultaDTO
        );
    }
}