package com.pol.springboot.app.librarydemo.services;

import com.pol.springboot.app.librarydemo.dto.auth.LoginRequestDTO;
import com.pol.springboot.app.librarydemo.dto.auth.LoginResponseDTO;
import com.pol.springboot.app.librarydemo.model.Usuario;
import com.pol.springboot.app.librarydemo.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Credenciales incorrectas")
                );

        if (!passwordEncoder.matches(
                dto.getPassword(),
                usuario.getPassword()
        )) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getRol().getTipo().name(),
                "NO_TOKEN_AUN"
        );
    }
}
