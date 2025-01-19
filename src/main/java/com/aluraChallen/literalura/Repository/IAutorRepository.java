package com.aluraChallen.literalura.Repository;

import com.aluraChallen.literalura.Models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.cumpleanios <= :anio AND (a.fechaFallecimiento >= :anio OR a.fechaFallecimiento IS NULL)")
    List<Autor> findAutoresVivosEnAnio(@Param("anio") int anio);


}
