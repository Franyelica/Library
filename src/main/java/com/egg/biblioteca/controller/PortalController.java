package com.egg.biblioteca.controller;


import com.egg.biblioteca.exception.MiException;
import com.egg.biblioteca.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalController {

    @Autowired
    private  UsuarioService usuarioService;

    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email,
                           @RequestParam String password, String password2, ModelMap model){

        try {
            usuarioService.registrarUsuario(nombre, email, password, password2);
            model.addAttribute("exito", "Usuario registrado correctamente");
            model.put("nombre", nombre);
            model.put("email", email);
            return "index.html";
        }catch (MiException ex){
            model.addAttribute("error", ex.getMessage());
            return "registro.html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model){
        if (error != null){
            model.put("error", "Usuario o contraseña invalidos!");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(){
        return "inicio.html";
    }
}
