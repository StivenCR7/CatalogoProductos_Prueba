package com.storeApi.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.storeApi.web.model.Categoria;
import com.storeApi.web.repository.CategoriaRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoriaController {
	
	@Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

}
