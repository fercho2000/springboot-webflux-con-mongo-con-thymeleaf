package com.bolsadeideas.springboot.webflux.springwebflux.controller;

import com.bolsadeideas.springboot.webflux.springwebflux.models.dao.ProductoRepository;
import com.bolsadeideas.springboot.webflux.springwebflux.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;


@Controller
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping({"listar", "/"})
    private String listar(Model model) {
        Flux<Producto> productos = productoRepository.findAll().map(
                producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                });
        productos.subscribe(prod -> {
            LOGGER.info(prod.getNombre());
        });

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Lista de productos");
        return "listar";
    }

    @GetMapping("listar-data-drive")
    private String listarDataDrive(Model model) {
        Flux<Producto> productos = productoRepository.findAll().map(
                producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                }).delayElements(Duration.ofSeconds(1));
        productos.subscribe(prod -> {
            LOGGER.info(prod.getNombre());
        });
        /* El uso de ReactiveDataDriverContextVariable es para simular el trabajo de
        retornar valores a un cliente de manera en cierto tiempo, ose al  manejo
        de la contrapresión, basandonos en el tamaño de nuestra lista(Buffer)
         */
        model.addAttribute("productos",
                new ReactiveDataDriverContextVariable(productos, 2));
        model.addAttribute("titulo", "Lista de productos");
        return "listar";
    }

}
