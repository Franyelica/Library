package com.egg.biblioteca.controller;

import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/editorial")
public class EditorialController{

    @Autowired private EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        try {
            editorialService.createEditorial(nombre);
            modelo.put("exito", "La editorial fue creada con exito");
        }catch (MiException ex){
            /*Logger.getLogger(EditorialController.class.getName()).log(Level.SEVERE, null, ex);*/
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }
        return "index.html";
    }
}
