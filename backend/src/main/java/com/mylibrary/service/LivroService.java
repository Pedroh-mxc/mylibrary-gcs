package com.mylibrary.service;

import com.mylibrary.entity.Livro;
import com.mylibrary.enums.StatusLivro;
import com.mylibrary.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro salvar(Livro livro) {

        livro.setStatus(StatusLivro.DISPONIVEL);

        return repository.save(livro);
    }

    public void deletar(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow();

        if (livro.getStatus() ==
                StatusLivro.EMPRESTADO) {

            throw new RuntimeException(
                    "Livro emprestado");
        }

        repository.delete(livro);
    }
}