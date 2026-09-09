package br.com.fiap.clyvopaws.auth;

import br.com.fiap.clyvopaws.domain.tutor.Tutor;
import br.com.fiap.clyvopaws.domain.tutor.TutorRepository;
import br.com.fiap.clyvopaws.domain.veterinario.Veterinario;
import br.com.fiap.clyvopaws.domain.veterinario.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationService {

    private final TutorRepository tutorRepository;
    private final VeterinarioRepository veterinarioRepository;

    public String currentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new AccessDeniedException("Usuário não autenticado.");
        }
        return auth.getName();
    }

    public boolean hasRole(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
    }

    public boolean isAdmin() {
        return hasRole("ADMIN");
    }

    public Tutor currentTutorOrNull() {
        return tutorRepository.findByUserUsername(currentUsername()).orElse(null);
    }

    public Veterinario currentVeterinarioOrNull() {
        return veterinarioRepository.findByUserUsername(currentUsername()).orElse(null);
    }

    public void assertSelfTutor(Long tutorId) {
        if (isAdmin()) return;
        Tutor tutor = currentTutorOrNull();
        if (tutor == null || tutorId == null || !tutor.getId().equals(tutorId)) {
            throw new AccessDeniedException("Você só pode acessar ou alterar seus próprios dados.");
        }
    }

    public void assertSelfVeterinario(Long veterinarioId) {
        if (isAdmin()) return;
        Veterinario vet = currentVeterinarioOrNull();
        if (vet == null || veterinarioId == null || !vet.getId().equals(veterinarioId)) {
            throw new AccessDeniedException("Você só pode acessar ou alterar sua própria agenda/perfil.");
        }
    }
}
