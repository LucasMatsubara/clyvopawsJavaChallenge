package br.com.fiap.clyvopaws.domain.catalogo;

import br.com.fiap.clyvopaws.domain.pet.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoPreventivoRepository extends JpaRepository<CatalogoPreventivo, Long> {
    List<CatalogoPreventivo> findByEspecie(Especie especie);
}
