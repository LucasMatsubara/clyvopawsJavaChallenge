package br.com.fiap.clyvopaws.domain.tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}