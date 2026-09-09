package br.com.fiap.clyvopaws.auth;

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

    public record LoginRequest(String username, String password) {}
    public record LoginResponse(String token) {}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        return new LoginResponse(tokenService.generateToken(auth.getName()));
    }

}