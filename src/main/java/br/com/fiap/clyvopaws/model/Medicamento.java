package br.com.fiap.clyvopaws.model;

import br.com.fiap.clyvopaws.enums.StatusMedicamento;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tb_medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String dosagem;

    @Column(nullable = false, length = 100)
    private String frequencia;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private Integer duracaoDias;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusMedicamento status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL)
    private List<HistoricoDose> historicoDoses;
}