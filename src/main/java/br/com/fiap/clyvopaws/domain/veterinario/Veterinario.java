package br.com.fiap.clyvopaws.domain.veterinario;

import br.com.fiap.clyvopaws.domain.clinica.Clinica;
import br.com.fiap.clyvopaws.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_veterinario")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    private String fotoUrl;

    @Column(nullable = false, unique = true, length = 20)
    private String crmv;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;
}