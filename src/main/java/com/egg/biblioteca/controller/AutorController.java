package com.egg.biblioteca.controller;

import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
