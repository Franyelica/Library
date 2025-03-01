package com.egg.biblioteca.controller;


import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.services.AutorService;
import com.egg.biblioteca.services.EditorialService;
import com.egg.biblioteca.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    LibroService libroService;

    @Autowired
    AutorService autorService;

    @Autowired
    EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar(){
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false)Long isbn,
                           @RequestParam String titulo, @RequestParam(required = false) Integer ejemplares,
                           @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo){
        try {
            libroService.createLibro(isbn, ejemplares,titulo, idAutor,idEditorial);
            modelo.put("exito","El libro fue creado correctamente");
        } catch (MiException ex){
            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }
}
