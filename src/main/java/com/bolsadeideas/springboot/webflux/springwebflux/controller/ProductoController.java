package com.bolsadeideas.springboot.webflux.springwebflux.controller;

import com.bolsadeideas.springboot.webflux.springwebflux.models.dao.ProductoRepository;
import com.bolsadeideas.springboot.webflux.springwebflux.models.documents.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;


@Controller
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping({"listar", "/"})
    private String listar(Model model) {
        Flux<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Lista de productos");
        return "listar";
    }

}
