package fiap.com.br.clyvovet.repository;

import fiap.com.br.clyvovet.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByTelefone(String telefone);
}