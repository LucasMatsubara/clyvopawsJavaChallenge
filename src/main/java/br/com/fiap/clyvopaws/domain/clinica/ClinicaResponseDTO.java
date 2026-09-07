package br.com.fiap.clyvopaws.domain.clinica;

public record ClinicaResponseDTO(Long id, String nome, String cnpj, String telefone, String email) {
    public ClinicaResponseDTO(Clinica clinica) {
        this(clinica.getId(), clinica.getNome(), clinica.getCnpj(), clinica.getTelefone(), clinica.getEmail());
    }
}