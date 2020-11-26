package com.bolsadeideas.springboot.webflux.springwebflux.controller;

import com.bolsadeideas.springboot.webflux.springwebflux.models.dao.ProductoRepository;
import com.bolsadeideas.springboot.webflux.springwebflux.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {
    @Autowired
    private ProductoRepository productoRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping()
    public Flux<Producto> index() {
        Flux<Producto> productos = productoRepository.findAll().map(
                producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                }).doOnNext(prod -> {
            LOGGER.info(prod.getNombre());
        });
        return productos;
    }

    @GetMapping("/{id}")
    public Mono<Producto> showById(@PathVariable String id) {
        // forma simple Mono<Producto> producto = productoRepository.findById(id);
        Flux<Producto> productos = productoRepository.findAll();
        Mono<Producto> producto = productos.filter(p ->
                p.getId().equals(id)).next().doOnNext(prod -> {
            LOGGER.info(prod.getNombre());
        });

        return producto;
    }
}
