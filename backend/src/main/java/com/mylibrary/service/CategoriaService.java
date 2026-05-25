package com.mylibrary.service;

import com.mylibrary.entity.Categoria;
import com.mylibrary.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria salvar(Categoria categoria) {

        repository.findByNome(categoria.getNome())
                .ifPresent(c -> {
                    throw new RuntimeException("Categoria já cadastrada");
                });

        return repository.save(categoria);
    }

    public void deletar(Long id) {

        Categoria categoria = repository.findById(id)
                .orElseThrow();

        if (!categoria.getLivros().isEmpty()) {
            throw new RuntimeException(
                    "Categoria possui livros vinculados");
        }

        repository.delete(categoria);
    }
}