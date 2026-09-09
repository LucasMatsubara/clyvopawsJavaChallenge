package br.com.fiap.clyvopaws.domain.consulta;

import br.com.fiap.clyvopaws.domain.agendamento.Agendamento;
import br.com.fiap.clyvopaws.domain.clinica.Clinica;
import br.com.fiap.clyvopaws.domain.medicamento.Medicamento;
import br.com.fiap.clyvopaws.domain.pet.Pet;
import br.com.fiap.clyvopaws.domain.veterinario.Veterinario;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tb_consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 255)
    private String resumo;

    @Column(length = 255)
    private String diagnostico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id", nullable = false)
    private Clinica clinica;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Medicamento> medicamentos;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos;

}