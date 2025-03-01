package com.egg.biblioteca.services;


import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.repository.EditorialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EditorialService {

    @Autowired
    EditorialRepository editorialRepository;

    @Transactional
    public void createEditorial(String nombre) throws MiException{
            validar(nombre);
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorialRepository.save(editorial);
    }

    @Transactional
    public List<Editorial> listarEditoriales(){

        List <Editorial> editoriales = new ArrayList<>();
        editoriales = editorialRepository.findAll();
        return editoriales;
    }

    @Transactional
    public void modificarEditorial(String id, String nombre)throws MiException{
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepository.findById(id);

        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            /*editorialRepository.save(editorial);*/
        }
    }

    private void validar(String nombre) throws MiException {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
    }
}
