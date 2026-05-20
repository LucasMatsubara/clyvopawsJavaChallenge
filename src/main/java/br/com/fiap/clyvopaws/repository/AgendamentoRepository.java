package br.com.fiap.clyvopaws.repository;

import br.com.fiap.clyvopaws.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByConsultaId(Long consultaId);
}
