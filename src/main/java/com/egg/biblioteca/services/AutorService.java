package com.egg.biblioteca.services;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    @Transactional
    public void createAutor(String nombre) throws MiException{
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autorRepository.save(autor);
    }

    @Transactional
    public List<Autor> listarAutores(){
        List <Autor> autores = new ArrayList<>();
        autores = autorRepository.findAll();
        return autores;
    }

    @Transactional
    public void modifyAutor(String nombre, String id) throws MiException{
        validar(nombre);
        Optional<Autor> answer = autorRepository.findById(id);
        if (answer.isPresent()){
            Autor autor = answer.get();
            autor.setNombre(nombre);
            autorRepository.save(autor);
        }
    }

    private void validar(String nombre) throws MiException{
            if (nombre == null || nombre.isEmpty()) {
                throw new MiException("el nombre no puede ser nulo o estar vacío");
            }
        }

    @Transactional
    public Autor getOne(String id){
        return autorRepository.getReferenceById(id);
    }
}

