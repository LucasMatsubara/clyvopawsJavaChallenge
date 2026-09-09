package br.com.fiap.clyvopaws.domain.veterinario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Optional<Veterinario> findByUserUsername(String username);
    boolean existsByClinicaId(Long clinicaId);
}