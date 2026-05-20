package br.com.fiap.clyvopaws.repository;

import br.com.fiap.clyvopaws.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByConsultaId(Long consultaId);
}
