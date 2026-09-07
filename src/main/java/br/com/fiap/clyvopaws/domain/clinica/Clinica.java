package br.com.fiap.clyvopaws.domain.clinica;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_clinica")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 20)
    private String cnpj;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String email;
}