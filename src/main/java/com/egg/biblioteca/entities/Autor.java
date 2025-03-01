package com.egg.biblioteca.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;

import java.util.UUID;

@Entity
public class Autor {

    @Id
    /*@GeneratedValue(strategy = GenerationType.AUTO)*/
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String nombre;

    //CONSTRUCTORES
    public Autor(){}


    //GETTERS


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    //SETTERS


    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Autor [id=" + id + ", nombre=" + nombre + "]";
    }
}
