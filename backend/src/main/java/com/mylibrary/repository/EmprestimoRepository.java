package com.mylibrary.repository;

import com.mylibrary.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmprestimoRepository
        extends JpaRepository<Emprestimo, Long> {

    Long countByDataDevolucaoEfetivaIsNull();

    @Query("""
        SELECT e FROM Emprestimo e
        WHERE e.dataDevolucaoPrevista < CURRENT_DATE
        AND e.dataDevolucaoEfetiva IS NULL
    """)
    List<Emprestimo> buscarAtrasados();
}