package com.Atv1B2.demo.controller;


import com.Atv1B2.demo.model.Jogo;
import com.Atv1B2.demo.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService service;

    public JogoController(JogoService service) {
        this.service = service;
    }

    // GET /jogos -> Response (200 OK)
    @GetMapping
    public ResponseEntity<List<Jogo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /jogos/{id} -> Response (200 OK)
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarPorId(@PathVariable Long id) {
        Optional<Jogo> jogo = service.buscarPorId(id);
        return jogo.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /jogos -> Response (201 Created)
    @PostMapping
    public ResponseEntity<Jogo> criar(@Valid @RequestBody Jogo jogo) {
        Jogo novoJogo = service.salvar(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);
    }

    // PUT /jogos/{id} -> Response (200 OK)
    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizar(@PathVariable Long id, @Valid @RequestBody Jogo jogo) {
        Optional<Jogo> jogoAtualizado = service.atualizar(id, jogo);
        return jogoAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /jogos/{id} -> Response (204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            // Retorna 204 No Content, que nativamente já não possui corpo de resposta
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
