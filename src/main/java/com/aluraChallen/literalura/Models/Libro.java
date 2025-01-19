package com.aluraChallen.literalura.Models;

import com.aluraChallen.literalura.Models.Records.DatosLibro;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long libroId;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private Long cantidadDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.libroId = datosLibro.libroId();
        this.titulo = datosLibro.titulo();
        if (datosLibro.autor() != null && !datosLibro.autor().isEmpty()) {
            this.autor = new Autor(datosLibro.autor().get(0));
        } else {
            this.autor = null;
        }
        this.idioma = idiomaModificado(datosLibro.idioma());
        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    public Libro(Libro libro) {
    }

    //idiomas
    private String idiomaModificado(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "Desconocido";
        }
        return idiomas.get(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Long cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return
                "\n ----- LIBRO -----"+
                        " \n Titulo: " + titulo +
                        " \n Autor: " + (autor != null ? autor.getNombre() : "N/A")+
                        " \n Idioma: " + idioma +
                        " \n Numero de descargas: " + cantidadDescargas +
                "\n -----------------";
    }

}
