package com.egg.biblioteca.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class Editorial {

    @Id
    /*@GeneratedValue(strategy = GenerationType.AUTO)*/
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String nombre;

    public Editorial() {
    }

    //getters setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return "Editorial [id=" + id + ", nombre=" + nombre + "]";
    }
}
