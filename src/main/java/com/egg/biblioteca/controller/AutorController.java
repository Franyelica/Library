package com.egg.biblioteca.controller;

import com.egg.biblioteca.entities.Autor;
import com.egg.biblioteca.entities.Libro;
import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired private AutorService autorService;

    @GetMapping("/registrar")
    public String registrar(){
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        try {
             autorService.createAutor(nombre);
             modelo.put("exito", "El autor fue creado correctamente");
        } catch (MiException ex){
            /*Logger.getLogger(AutorController.class.getName()).log(Level.SEVERE, null, ex);*/
            modelo.put("error", "ocurrió un error:" + ex.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){

        List<Autor> autores = autorService.listarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("autor", autorService.getOne(id));

        return  "autor_modificar.html";
    }

    @PostMapping("/modificar/{id}")
        public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {
        try {
            autorService.modifyAutor(nombre, id);
            modelo.put("exito", "El autor fue modificado exitosamente");
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";
        }
    }
}
