package br.com.fiap.clyvopaws.repository;

import br.com.fiap.clyvopaws.model.HistoricoDose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoDoseRepository extends JpaRepository<HistoricoDose, Long> {
    List<HistoricoDose> findByMedicamentoId(Long medicamentoId);
}
