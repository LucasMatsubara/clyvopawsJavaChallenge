package br.com.fiap.clyvopaws.domain.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AgendaDisponivelRepository extends JpaRepository<AgendaDisponivel, Long> {
    List<AgendaDisponivel> findByVeterinarioIdAndDisponivelTrue(Long veterinarioId);
}