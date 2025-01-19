package com.aluraChallen.literalura.Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer cumpleanios;

    private Integer fechaFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCumpleanios() {
        return cumpleanios;
    }

    public void setCumpleanios(Integer cumpleanios) {
        this.cumpleanios = cumpleanios;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Autor(com.aluraChallen.literalura.Models.Records.Autor autor) {
        this.nombre = autor.nombre();
        this.cumpleanios = autor.cumpleanios();
        this.fechaFallecimiento = autor.fechaFallecimiento();
    }

    @Override
    public String toString() {
        return
                        "\n Autor: " + nombre +
                        "\n Fecha de nacimiento: " + cumpleanios +
                        "\n Fecha de fallecimiento: " + (fechaFallecimiento != null ? fechaFallecimiento.shortValue() : "SIGUE VIVO") +
                        "\n Libros: [" + libros.stream().map(libro -> libro.getTitulo()).collect(Collectors.joining(", "))  + "]";
    }
}
