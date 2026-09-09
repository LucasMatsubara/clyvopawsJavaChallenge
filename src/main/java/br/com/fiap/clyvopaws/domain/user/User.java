package br.com.fiap.clyvopaws.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Pattern(regexp = "TUTOR|VETERINARIO|ADMIN")
    private String role;

}