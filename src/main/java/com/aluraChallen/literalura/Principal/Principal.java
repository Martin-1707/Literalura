package com.aluraChallen.literalura.Principal;

import com.aluraChallen.literalura.Models.Autor;
import com.aluraChallen.literalura.Models.BusquedasLibro;
import com.aluraChallen.literalura.Models.Libro;
import com.aluraChallen.literalura.Models.Records.DatosLibro;
import com.aluraChallen.literalura.Repository.IAutorRepository;
import com.aluraChallen.literalura.Repository.ILibroRepository;
import com.aluraChallen.literalura.Service.ConsumoApi;
import com.aluraChallen.literalura.Service.ConvertirDatos;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.*;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvertirDatos convertir = new ConvertirDatos();
    private static String API_BASE = "https://gutendex.com/books/?search=";

    private ILibroRepository libroRepository;
    private IAutorRepository autorRepository;


    public Principal(ILibroRepository libroRepository, IAutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

public void menu(){
    var opcion = -1;
    while (opcion != 0){
        var menu = """
            ***************************************
            *     BIENVENIDO A LA LIBRERÍA       *
            *           ALURA LITERARIA          *
            ***************************************
            
            Elija una opción:
            
            1 - Buscar libro por título
            2 - Listar libros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos en un año específico
            5 - Listar libros por idioma
            
            0 - Salir
            
            ***************************************
            *   Por favor, ingrese una opción    *
            ***************************************
            """;

        try {
            System.out.println(menu);
            opcion = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {

            System.out.println("****************************************");
            System.out.println("  ¡Por favor, ingrese un número válido! ");
            System.out.println("****************************************\n");
            sc.nextLine();
            continue;
        }


        switch (opcion){
            case 1:
                buscarLibroPorTitulo();
                break;
            case 2:
                librosRegistrados();
                break;
            case 3:
                ListarAutoresRegistrados();
                break;
            case 4:
                ListarAutoresVivosEnUnDeterminadoAnio();
                break;
            case 5:
                ListarLibrosPorUnIdioma();
                break;


            case 0:
                opcion = 0;
                System.out.println("|********************************|");
                System.out.println("|    Aplicación cerrada. Bye!    |");
                System.out.println("|********************************|\n");
                break;
            default:
                System.out.println("|*********************|");
                System.out.println("|  Opción Incorrecta. |");
                System.out.println("|*********************|\n");
                System.out.println("Intente con una nueva Opción:");
                menu();
                break;
        }
    }
}
private Libro getDatosLibro(){
    System.out.println("Ingrese el nombre del libro: ");
    var nombreLibro = sc.nextLine().toLowerCase();
    var json = consumoApi.obtenerDatos(API_BASE + nombreLibro.replace(" ", "%20"));
    BusquedasLibro datos = convertir.obtenerDatosJSON(json, BusquedasLibro.class);

    if (datos != null && datos.getResultadoLibros() != null && !datos.getResultadoLibros().isEmpty()) {
        DatosLibro primerLibro = datos.getResultadoLibros().get(0);
        return new Libro(primerLibro);
    } else {
        System.out.println("No se encontraron resultados.");
        return null;
    }
}

//Opcion 1
private void buscarLibroPorTitulo() {

    Libro libro = getDatosLibro();

    if (libro == null){
        System.out.println("Libro no encontrado.");
        return;
    }

    try{
        boolean libroExists = libroRepository.existsByTitulo(libro.getTitulo());
        if (libroExists){
            System.out.println("El libro ya existe en la base de datos!");
        }else {
            libroRepository.save(libro);
            System.out.println(libro.toString());
        }
    }catch (InvalidDataAccessApiUsageException e){
        System.out.println("No se puede persisitir el libro buscado!");
    }
    System.out.println("\nPresione ENTER para continuar...");
    sc.nextLine();

}

//Opcion 2
private void librosRegistrados(){
    List<Libro> libros = libroRepository.findAll();
    if (libros.isEmpty()) {
        System.out.println("No se encontraron libros en la base de datos.");
    } else {
        System.out.println("Libros encontrados en la base de datos:");
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }
    System.out.println("\nPresione ENTER para continuar...");
    sc.nextLine();
}

//Opcion 3
private void ListarAutoresRegistrados(){
    List<Autor> autores = autorRepository.findAll();
    if (autores.isEmpty()) {
        System.out.println("No se encontraron autores en la base de datos.");
    } else {
        System.out.println("Autores encontrados en la base de datos:");

        Map<String, Autor> autoresMap = new HashMap<>();

        for (Autor autor : autores) {
            if (!autoresMap.containsKey(autor.getNombre())) {
                autoresMap.put(autor.getNombre(), autor);
            } else {
                Autor autorExistente = autoresMap.get(autor.getNombre());
                autorExistente.getLibros().addAll(autor.getLibros());
            }
        }
        for (Autor autor : autoresMap.values()) {
            System.out.println(autor.toString());
        }
    }
    System.out.println("\nPresione ENTER para continuar...");
    sc.nextLine();
}

//Opcion 4
private void ListarAutoresVivosEnUnDeterminadoAnio(){
    System.out.println("Ingrese el año: ");

    int anio;

    while (true) {
        try {
            anio = sc.nextInt();
            sc.nextLine();
            if (anio < 1000 || anio > 9999) {
                System.out.println("Por favor, ingrese un año válido.");
            } else {
                break;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número de año.");
            sc.nextLine();
        }
    }

    List<Autor> autores = autorRepository.findAutoresVivosEnAnio(anio);

    if (autores.isEmpty()) {
        System.out.println("No se encontraron autores vivos en el año " + anio + ".");
    } else {
        System.out.println("Autores vivos en el año " + anio + ":");
        for (Autor autor : autores) {
            System.out.println(autor.toString());
        }
        System.out.println("\nPresione ENTER para continuar...");
        sc.nextLine();
    }
    }

//Opcion 5
private void ListarLibrosPorUnIdioma() {
    //opciones
    System.out.println("Opciones de idioma:");
    System.out.println("es - español");
    System.out.println("en - inglés");
    System.out.println("fr - francés");
    System.out.println("de - alemán");
    System.out.println("it - italiano");
    System.out.println("pt - portugués");

    //ELEGIR
    System.out.println("Ingrese el idioma: ");
    var idioma = sc.nextLine();

    long cantidadLibros = libroRepository.ContarIdiomasSimilares(idioma);

    if (cantidadLibros == 0) {
        System.out.println("No se encontraron libros en la base de datos.");
    } else {
        System.out.println("Cantidad de libros en el idioma '" + idioma + "': " + cantidadLibros);

        List<Libro> libros = libroRepository.findByIdioma(idioma);
        System.out.println("Libros encontrados en la base de datos:");

        for (Libro libro : libros) {
            if (libro.getIdioma().equals(idioma)){
                System.out.println(libro.toString());
            }
        }
    }
    System.out.println("\nPresione ENTER para continuar...");
    sc.nextLine();
}
}
