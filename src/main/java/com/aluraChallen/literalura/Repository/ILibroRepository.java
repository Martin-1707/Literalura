package com.aluraChallen.literalura.Repository;

import com.aluraChallen.literalura.Models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTitulo(String titulo);

    @Query("SELECT COUNT(l) FROM Libro l WHERE l.idioma = :idioma")
    long ContarIdiomasSimilares(String idioma);

    List<Libro> findByIdioma(String idioma);
}
