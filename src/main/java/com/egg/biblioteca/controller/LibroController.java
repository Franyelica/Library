package com.egg.biblioteca.controller;


import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Editorial;
import com.egg.biblioteca.entities.Libro;
import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.services.AutorService;
import com.egg.biblioteca.services.EditorialService;
import com.egg.biblioteca.services.LibroService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public String registrar(ModelMap modelo){
        List<Autor> autores = autorService.listarAutores();
        List<Editorial> editoriales = editorialService.listarEditoriales();
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false)Long isbn,
                           @RequestParam String titulo, @RequestParam(required = false) Integer ejemplares,
                           @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo){
        if (isbn == null || isbn <= 0){
            modelo.addAttribute("error","no sirve");
            return "libro_form.html";
        }
        try {
            libroService.createLibro(isbn, ejemplares,titulo, idAutor,idEditorial);
            modelo.addAttribute("exito","El libro fue creado correctamente");
        } catch (MiException ex){
            modelo.addAttribute("error", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";

    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){

        List<Libro> libros = libroService.listarLibros();
        modelo.addAttribute("libros", libros);
        return "libro_list.html";
    }

    @GetMapping("/modificar/{isbn}")
        public String modificar(@PathVariable Long isbn, ModelMap modelo){
            modelo.put("libro", libroService.getOne(isbn));
            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            return "libro_modificar.html";
    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, Integer ejemplares, String titulo, String idAutor, String idEditorial, ModelMap modelo){
        try {
            List<Autor> autores = autorService.listarAutores();
            List<Editorial> editoriales = editorialService.listarEditoriales();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            libroService.modifyLibro(isbn, ejemplares, titulo, idAutor, idEditorial);
            modelo.put("exito", "El libro fue modificado ecitosamente");
            return  "redirect:../lista";
        }catch (MiException ex){
            modelo.put("error", ex.getMessage());
            return  "libro_modificar.html";
        }
    }
}