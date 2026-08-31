package br.com.fiap.clyvopaws.domain.pet;

import br.com.fiap.clyvopaws.domain.consulta.Consulta;
import br.com.fiap.clyvopaws.domain.tutor.Tutor;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tb_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Especie especie;

    @Column(nullable = false, length = 50)
    private String raca;

    private Double peso;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Sexo sexo;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 500)
    private String descricao;

    private String fotoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Consulta> historicoConsultas;
}