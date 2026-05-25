package com.mylibrary.service;

import com.mylibrary.entity.Emprestimo;
import com.mylibrary.entity.Livro;
import com.mylibrary.enums.StatusLivro;
import com.mylibrary.repository.EmprestimoRepository;
import com.mylibrary.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository repository;
    private final LivroRepository livroRepository;

    public List<Emprestimo> listar() {
        return repository.findAll();
    }

    public Emprestimo emprestar(Emprestimo e) {

        Livro livro = livroRepository
                .findById(e.getLivro().getId())
                .orElseThrow();

        if (livro.getStatus() ==
                StatusLivro.EMPRESTADO) {

            throw new RuntimeException(
                    "Livro já emprestado");
        }

        livro.setStatus(StatusLivro.EMPRESTADO);

        livroRepository.save(livro);

        e.setDataEmprestimo(LocalDate.now());

        return repository.save(e);
    }

    public Emprestimo devolver(Long id) {

        Emprestimo e = repository.findById(id)
                .orElseThrow();

        if (e.getLivro().getStatus() ==
                StatusLivro.DISPONIVEL) {

            throw new RuntimeException(
                    "Livro já devolvido");
        }

        e.setDataDevolucaoEfetiva(LocalDate.now());

        Livro livro = e.getLivro();

        livro.setStatus(StatusLivro.DISPONIVEL);

        livroRepository.save(livro);

        return repository.save(e);
    }
}