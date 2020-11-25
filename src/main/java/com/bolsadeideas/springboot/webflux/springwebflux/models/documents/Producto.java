package com.bolsadeideas.springboot.webflux.springwebflux.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class Producto {
    @Id
    private String id;

    private String nombre;
    private Double precio;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /* igual que en spring y jpa  cuando los objetos son manejados por los frameworks, en este caso
     spring data mongo, debemos generar un constructor vacio, para que así se
      puedan crear los objetos o instancias

    */

    public Producto() {
    }

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
