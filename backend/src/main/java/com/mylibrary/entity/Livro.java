package com.mylibrary.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mylibrary.enums.StatusLivro;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    private String isbn;

    private Integer ano;

    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("livros")
    private Categoria categoria;

    @OneToMany(mappedBy = "livro")
    @JsonIgnoreProperties("livro")
    private List<Emprestimo> emprestimos = new ArrayList<>();
}