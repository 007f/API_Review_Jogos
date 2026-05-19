package com.Atv1B2.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class AuthController {

    // POST /login -> Realiza a autenticação básica
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {

        // Regra estrita: email == "usuario@esoft.com" e password == "Abc123" [cite: 18, 20]
        if ("usuario@esoft.com".equals(request.email()) && "Abc123".equals(request.password())) {

            // Retorna um UUID dinâmico como token [cite: 18, 22]
            String token = UUID.randomUUID().toString();

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            // Response (200 OK) [cite: 21]
            return ResponseEntity.ok(response);
        }

        // Se as credenciais estiverem incorretas, retorna 401 Unauthorized
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

/**
 * Record simples para mapear o corpo da requisição (Request Body).
 * O Spring converterá automaticamente o JSON {"email": "...", "password": "..."} para este objeto. [cite: 19, 20]
 */
record LoginRequest(String email, String password) {}