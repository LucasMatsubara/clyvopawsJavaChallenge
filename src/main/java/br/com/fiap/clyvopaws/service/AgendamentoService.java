package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.AgendamentoRequestDTO;
import br.com.fiap.clyvopaws.dto.AgendamentoResponseDTO;
import br.com.fiap.clyvopaws.model.Agendamento;
import br.com.fiap.clyvopaws.model.Consulta;
import br.com.fiap.clyvopaws.repository.AgendamentoRepository;
import br.com.fiap.clyvopaws.repository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final ConsultaRepository consultaRepository;

    @Transactional
    public AgendamentoResponseDTO cadastrar(AgendamentoRequestDTO request) {
        Consulta consulta = consultaRepository.findById(request.consultaId()).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(request.dataHora());
        agendamento.setTitulo(request.titulo());
        agendamento.setDescricao(request.descricao());
        agendamento.setConsulta(consulta);
        return toResponseDTO(agendamentoRepository.save(agendamento));
    }

    public AgendamentoResponseDTO buscarPorId(Long id) {
        Agendamento ag = agendamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));
        return toResponseDTO(ag);
    }

    public List<AgendamentoResponseDTO> listarPorConsulta(Long consultaId) {
        return agendamentoRepository.findByConsultaId(consultaId).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO request) {
        Agendamento ag = agendamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado."));
        ag.setDataHora(request.dataHora());
        ag.setTitulo(request.titulo());
        ag.setDescricao(request.descricao());
        return toResponseDTO(agendamentoRepository.save(ag));
    }

    @Transactional
    public void excluir(Long id) {
        if (!agendamentoRepository.existsById(id)) throw new EntityNotFoundException("Agendamento não encontrado.");
        agendamentoRepository.deleteById(id);
    }

    private AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        return new AgendamentoResponseDTO(agendamento.getId(), agendamento.getDataHora(), agendamento.getTitulo(), agendamento.getDescricao(), agendamento.getConsulta().getId());
    }
}