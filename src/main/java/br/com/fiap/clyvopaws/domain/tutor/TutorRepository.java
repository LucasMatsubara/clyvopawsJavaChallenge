package br.com.fiap.clyvopaws.domain.tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    Optional<Tutor> findByUserUsername(String username);
}