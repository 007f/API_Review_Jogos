package com.Atv1B2.demo.service;

import com.Atv1B2.demo.model.Jogo;
import com.Atv1B2.demo.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public List<Jogo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Jogo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Jogo salvar(Jogo jogo) {
        return repository.save(jogo);
    }

    public Optional<Jogo> atualizar(Long id, Jogo jogoAtualizado) {
        // Busca o jogo existente, se encontrar, atualiza os dados e salva
        return repository.findById(id).map(jogoExistente -> {
            jogoExistente.setNome(jogoAtualizado.getNome());
            jogoExistente.setTipo(jogoAtualizado.getTipo());
            jogoExistente.setNota(jogoAtualizado.getNota());
            jogoExistente.setReview(jogoAtualizado.getReview());
            return repository.save(jogoExistente);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
