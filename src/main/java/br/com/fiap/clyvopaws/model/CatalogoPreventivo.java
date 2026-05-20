package br.com.fiap.clyvopaws.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_catalogo_preventivo")
public class CatalogoPreventivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(nullable = false, length = 50)
    private String raca;

    @Column(nullable = false, length = 150)
    private String doencaPredisposta;

    private Integer idadeAlertaMeses;

    @Column(columnDefinition = "TEXT")
    private String dicaPrevencao;

    @Column(columnDefinition = "TEXT")
    private String cuidadosRecomendados;
}