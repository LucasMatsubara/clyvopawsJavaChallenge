package br.com.fiap.clyvopaws.domain.consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Page<Consulta> findByPetId(Long petId, Pageable pageable);
    Page<Consulta> findByVeterinarioId(Long veterinarioId, Pageable pageable);
    Page<Consulta> findByPetIdAndVeterinarioId(Long petId, Long veterinarioId, Pageable pageable);
}