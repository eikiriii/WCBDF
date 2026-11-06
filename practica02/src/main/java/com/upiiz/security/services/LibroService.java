package com.upiiz.security.services;

import com.upiiz.security.entities.Libro;
import com.upiiz.security.repositories.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository libroRepository;


    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> allLibros(){
        List<Libro> libros = new ArrayList<>();

        libroRepository.findAll().forEach(libros::add);

        return libros;
    }

    public Optional<Libro> getLibroByTitulo(String titulo) {
        //Verificar la clave y usuario conciden
        return libroRepository.findByTitulo(titulo);
    }

    public Optional<Libro> getLibroById(Long id) {
        //Verificar la clave y usuario conciden
        return libroRepository.findById(id);
    }

    //Guardar un libro
    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro updateLibro(Long id, Libro libro) {
        Libro actualizar = new Libro(libro.isDisponible(),libro.getPaginas(),libro.getEditorial(),libro.getAutor(),libro.getTitulo(), id);
        return libroRepository.save(actualizar);
    }

    public void deleteLibro(Long id){
        libroRepository.deleteById(id);
    }
}
