package br.com.fiap.clyvopaws.domain.medicamento;

import br.com.fiap.clyvopaws.auth.AuthorizationService;
import br.com.fiap.clyvopaws.domain.consulta.Consulta;
import br.com.fiap.clyvopaws.domain.consulta.ConsultaRepository;
import br.com.fiap.clyvopaws.domain.consulta.ConsultaResponseDTO;
import br.com.fiap.clyvopaws.domain.consulta.ConsultaService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicamentoService {
    private final MedicamentoRepository medicamentoRepository;
    private final ConsultaRepository consultaRepository;
    private final HistoricoDoseRepository historicoDoseRepository;
    private final ConsultaService consultaService;
    private final AuthorizationService authorizationService;

    private void assertPodeVer(Medicamento medicamento) {
        Consulta consulta = medicamento.getConsulta();
        if (authorizationService.isAdmin()) return;
        if (authorizationService.hasRole("VETERINARIO")) {
            authorizationService.assertSelfVeterinario(consulta.getVeterinario().getId());
            return;
        }
        authorizationService.assertSelfTutor(consulta.getPet().getTutor().getId());
    }

    @Transactional
    public MedicamentoResponseDTO cadastrar(MedicamentoRequestDTO request) {
        Consulta consulta = consultaRepository.findById(request.consultaId()).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        authorizationService.assertSelfVeterinario(consulta.getVeterinario().getId());
        Medicamento medicamento = new Medicamento();
        medicamento.setNome(request.nome());
        medicamento.setDosagem(request.dosagem());
        medicamento.setFrequencia(request.frequencia());
        medicamento.setDataInicio(request.dataInicio());
        medicamento.setDuracaoDias(request.duracaoDias());
        medicamento.setStatus(request.status());
        medicamento.setConsulta(consulta);
        return toResponseDTO(medicamentoRepository.save(medicamento));
    }

    @Transactional(readOnly = true)
    public MedicamentoResponseDTO buscarPorId(Long id) {
        Medicamento med = medicamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));
        assertPodeVer(med);
        return toResponseDTO(med);
    }

    @Transactional(readOnly = true)
    public Page<MedicamentoResponseDTO> listarTodos(Pageable pageable) {
        if (authorizationService.isAdmin()) {
            return medicamentoRepository.findAll(pageable).map(this::toResponseDTO);
        }

        var vet = authorizationService.currentVeterinarioOrNull();
        if (vet != null) {
            return medicamentoRepository.findByConsultaVeterinarioId(vet.getId(), pageable).map(this::toResponseDTO);
        }

        var tutor = authorizationService.currentTutorOrNull();
        if (tutor != null) {
            return medicamentoRepository.findByConsultaPetTutorId(tutor.getId(), pageable).map(this::toResponseDTO);
        }

        throw new AccessDeniedException("Acesso negado. Você precisa ser um Veterinário, Tutor ou Administrador.");
    }

    @Transactional(readOnly = true)
    public Page<MedicamentoResponseDTO> listarPorConsulta(Long consultaId, Pageable pageable) {
        Consulta consulta = consultaRepository.findById(consultaId).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        if (!authorizationService.isAdmin()) {
            if (authorizationService.hasRole("VETERINARIO")) {
                authorizationService.assertSelfVeterinario(consulta.getVeterinario().getId());
            } else {
                authorizationService.assertSelfTutor(consulta.getPet().getTutor().getId());
            }
        }
        return medicamentoRepository.findByConsultaId(consultaId, pageable).map(this::toResponseDTO);
    }

    @Transactional
    public HistoricoDoseResponseDTO registrarDose(HistoricoDoseRequestDTO request) {
        Medicamento medicamento = medicamentoRepository.findById(request.medicamentoId()).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));
        assertPodeVer(medicamento);
        HistoricoDose dose = new HistoricoDose();
        dose.setDataHoraToma(request.dataHoraToma());
        dose.setMedicamento(medicamento);
        dose = historicoDoseRepository.save(dose);
        return new HistoricoDoseResponseDTO(dose.getId(), dose.getDataHoraToma(), toResponseDTO(medicamento));
    }

    @Transactional
    public MedicamentoResponseDTO atualizar(Long id, MedicamentoRequestDTO request) {
        Medicamento med = medicamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));
        authorizationService.assertSelfVeterinario(med.getConsulta().getVeterinario().getId());
        med.setDosagem(request.dosagem());
        med.setFrequencia(request.frequencia());
        med.setStatus(request.status());
        return toResponseDTO(medicamentoRepository.save(med));
    }

    @Transactional
    public void excluir(Long id) {
        Medicamento med = medicamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));
        authorizationService.assertSelfVeterinario(med.getConsulta().getVeterinario().getId());
        medicamentoRepository.deleteById(id);
    }

    private MedicamentoResponseDTO toResponseDTO(Medicamento medicamento) {
        ConsultaResponseDTO consultaDTO = medicamento.getConsulta() != null
                ? new ConsultaResponseDTO(medicamento.getConsulta())
                : null;

        return new MedicamentoResponseDTO(
                medicamento.getId(),
                medicamento.getNome(),
                medicamento.getDosagem(),
                medicamento.getFrequencia(),
                medicamento.getDataInicio(),
                medicamento.getDuracaoDias(),
                medicamento.getStatus(),
                consultaDTO
        );
    }
}