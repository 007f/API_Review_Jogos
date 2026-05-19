package com.Atv1B2.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do jogo é obrigatório e não pode ser vazio.")
    private String nome;

    @NotBlank(message = "O tipo do jogo é obrigatório.")
    private String tipo;

    @NotNull(message = "A nota é obrigatória.")
    @Min(value = 0, message = "A nota mínima é 0.")
    @Max(value = 10, message = "A nota máxima é 10.")
    private Integer nota;

    @NotBlank(message = "A review não pode ficar em branco.")
    private String review;

    public Jogo() {
    }

    public Jogo(Long id, String nome, String tipo, int nota, String review) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.nota = nota;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
