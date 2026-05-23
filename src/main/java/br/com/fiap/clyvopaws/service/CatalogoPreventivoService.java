package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.CatalogoPreventivoResponseDTO;
import br.com.fiap.clyvopaws.enums.Especie;
import br.com.fiap.clyvopaws.model.CatalogoPreventivo;
import br.com.fiap.clyvopaws.repository.CatalogoPreventivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoPreventivoService {

    private final CatalogoPreventivoRepository catalogoPreventivoRepository;

    public List<CatalogoPreventivoResponseDTO> buscarPlanoPreventivo(Especie especie, String raca) {
        List<CatalogoPreventivo> diretrizes = catalogoPreventivoRepository.findByEspecieAndRacaIgnoreCase(especie.name(), raca);

        return diretrizes.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private CatalogoPreventivoResponseDTO toResponseDTO(CatalogoPreventivo catalogo) {
        return new CatalogoPreventivoResponseDTO(
                catalogo.getId(),
                catalogo.getEspecie(),
                catalogo.getRaca(),
                catalogo.getDoencaPredisposta(),
                catalogo.getIdadeAlertaMeses(),
                catalogo.getDicaPrevencao(),
                catalogo.getCuidadosRecomendados()
        );
    }
}
