package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Lembrete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    @Query("SELECT l FROM Lembrete l WHERE l.planoTratamento.pet.id = :petId AND l.dataHoraAlerta BETWEEN :inicioDia AND :fimDia")
    Page<Lembrete> findLembretesDoDiaPorPet(
            @Param("petId") Long petId,
            @Param("inicioDia") LocalDateTime inicioDia,
            @Param("fimDia") LocalDateTime fimDia,
            Pageable pageable);
}