package br.com.fiap.clyvopaws.domain.medicamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    Page<Medicamento> findByConsultaId(Long consultaId, Pageable pageable);
}