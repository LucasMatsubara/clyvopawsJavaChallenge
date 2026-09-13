package br.com.fiap.clyvopaws.auth;

import br.com.fiap.clyvopaws.domain.tutor.TutorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Login", description = "Endpoints para login de usuários")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final TutorRepository tutorRepository;

    public record LoginRequest(String username, String password) {}
    public record LoginResponse(String token, String nomeCompleto, String fotoUrl, Long id) {}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        String token = tokenService.generateToken(auth.getName());

        var tutor = tutorRepository.findByUserUsername(auth.getName()).orElse(null);
        String nome = (tutor != null) ? tutor.getNomeCompleto() : auth.getName();
        String foto = (tutor != null) ? tutor.getFotoUrl() : null;
        Long tutorId = (tutor != null) ? tutor.getId() : null;

        return new LoginResponse(token, nome, foto, tutorId);
    }

}