package com.bolsadeideas.springboot.webflux.springwebflux.models.dao;

import com.bolsadeideas.springboot.webflux.springwebflux.models.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {

}
