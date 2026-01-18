package com.pol.springboot.app.librarydemo.controller;

import com.pol.springboot.app.librarydemo.dto.auth.LoginRequestDTO;
import com.pol.springboot.app.librarydemo.dto.auth.LoginResponseDTO;
import com.pol.springboot.app.librarydemo.model.Usuario;
import com.pol.springboot.app.librarydemo.repository.UsuarioRepository;
import com.pol.springboot.app.librarydemo.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(
            UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {

        Usuario usuario = usuarioRepository
                .findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        // 🔐 JWT con ID (uid), email y rol
        String token = jwtUtil.generarToken(
                usuario.getId(),                       // 👈 PK
                usuario.getEmail(),
                usuario.getRol().getTipo().name()
        );

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getRol().getTipo().name(),
                token
        );
    }
}
