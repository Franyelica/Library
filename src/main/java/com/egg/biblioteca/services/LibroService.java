package com.egg.biblioteca.services;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.entities.Libro;
import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.repository.AutorRepository;
import com.egg.biblioteca.repository.EditorialRepository;
import com.egg.biblioteca.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibroService {
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public void createLibro(Long isbn, Integer ejemplares, String titulo, String idAutor, String idEditorial) throws MiException{

        validar(isbn,ejemplares, titulo, idAutor, idEditorial);
        Autor autor = autorRepository.findById(idAutor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepository.save(libro);
    }

    @Transactional
    public List<Libro> listarLibros(){
        List<Libro> libros = new ArrayList<>();
        libros = libroRepository.findAll();
        return libros;
    }

    @Transactional
    public void modifyLibro(Long isbn, Integer ejemplares, String titulo, String idAutor, String idEditorial) throws MiException{
        validar(isbn, ejemplares, titulo, idAutor, idEditorial);
        Optional<Libro> respuesta = libroRepository.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepository.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepository.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            /*libroRepository.save(libro);*/
        }
    }

    private void validar(Long isbn, Integer ejemplares, String titulo, String idAutor, String idEditorial) throws MiException {
        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("el titulo no puede ser nulo o estar vacío");
        }
        if (idAutor == null || idAutor.isEmpty()){
            throw new MiException("el autor no puede ser nulo o estar vacío");
        }
        if (idEditorial == null){
            throw new MiException("la editorial no puede ser nulo o estar vacío");
        }
        if (ejemplares == null) {
            throw new MiException("ejemplares no puede ser nulo");
        }
        if (isbn == null){
            throw new MiException("el isbn no puede ser nulo");
        }
    }
}
