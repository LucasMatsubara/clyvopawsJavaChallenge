package br.com.fiap.clyvopaws.domain.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByConsultaId(Long consultaId);
    List<Agendamento> findByConsultaPetId(Long petId);

    @Query("SELECT a FROM Agendamento a WHERE a.consulta.pet.tutor.id = :tutorId ORDER BY a.dataHora ASC")
    List<Agendamento> findByTutorId(@Param("tutorId") Long tutorId);
}
