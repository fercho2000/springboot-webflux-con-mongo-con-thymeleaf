package com.bolsadeideas.springboot.webflux.springwebflux;

import com.bolsadeideas.springboot.webflux.springwebflux.models.dao.ProductoRepository;
import com.bolsadeideas.springboot.webflux.springwebflux.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringWebfluxApplication implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    //Componente ReactiveMongoTemplate, permite borrar colecciones
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringWebfluxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Eliminar todos los registros de la coleccion productos , cuando se arranque la app
        reactiveMongoTemplate.dropCollection("productos").subscribe();


        // inserts de ejemplo, que serviran como un tipo migración o sql builder
        Flux.just(
                new Producto("TV samsung pantalla pequeña", 1200.00),
                new Producto("TV samsung pantalla mediana", 17200.00),
                new Producto("Computador dell", 22000.00),
                new Producto("Play station 5", 690000.00),
                new Producto("TV Pansasonic", 1400.00),
                new Producto("TV samsung pantalla grande", 19200.00),
                new Producto("Computador dell", 22000.00),
                new Producto("Xbox one S", 690000.00))
                .flatMap(producto -> productoRepository.save(producto))
                .subscribe(producto -> LOGGER.info("Insert: "
                        + producto.getId() + " " + producto.getNombre()));

        // No se hace uso del map porque este nos devuelve el mono del producto, osea un único producto,
        // en cambio con flatmap es obtener observable, aplana el flux o mono y lo aplana y se combierte en
        //objeto producto, el fltamao esta preparado para trabar sea con save o mono
    }
}
