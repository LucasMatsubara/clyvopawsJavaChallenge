package br.com.fiap.clyvopaws.domain.medicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoDoseRepository extends JpaRepository<HistoricoDose, Long> {
    List<HistoricoDose> findByMedicamentoId(Long medicamentoId);
}
