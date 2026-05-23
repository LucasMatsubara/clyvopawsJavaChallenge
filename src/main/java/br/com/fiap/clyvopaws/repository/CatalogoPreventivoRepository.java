package br.com.fiap.clyvopaws.repository;

import br.com.fiap.clyvopaws.model.CatalogoPreventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoPreventivoRepository extends JpaRepository<CatalogoPreventivo, Long> {

    @Query("SELECT c FROM CatalogoPreventivo c WHERE c.especie = :especie AND UPPER(c.raca) = UPPER(:raca)")
    List<CatalogoPreventivo> findByEspecieAndRacaIgnoreCase(@Param("especie") String especie, @Param("raca") String raca);
}
