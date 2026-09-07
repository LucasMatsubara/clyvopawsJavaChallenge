package br.com.fiap.clyvopaws.domain.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}