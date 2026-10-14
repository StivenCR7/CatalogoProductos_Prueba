package com.storeApi.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeApi.web.model.Categoria;
import com.storeApi.web.model.Productos;
import com.storeApi.web.repository.CategoriaRepository;
import com.storeApi.web.repository.ProductosRepository;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProdcutosController {
	
	@Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    
    @GetMapping
    public ResponseEntity<List<Productos>> listarProductos() {
        return ResponseEntity.ok(productosRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable(value = "id") Integer id) {
        Optional<Productos> prod = productosRepository.findById(id);
        if (prod.isPresent()) {
            return ResponseEntity.ok(prod.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Integer id, @RequestBody Productos productDetails) {
        Optional<Productos> prodOpt = productosRepository.findById(id);
        if (prodOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }

        Productos producto = prodOpt.get();

        if (productDetails.getNombre() == null || productDetails.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio y no puede estar vacío.");
        }

      
        if (productDetails.getPrecio() == null || productDetails.getPrecio() < 0) {
            return ResponseEntity.badRequest().body("El precio debe ser mayor a 0.");
        }

        
        if (productDetails.getStock() == null || productDetails.getStock() < 0) {
            return ResponseEntity.badRequest().body("El stock debe ser un número entero mayor o igual a 0.");
        }

        
        if (productDetails.getCategoria() == null || productDetails.getCategoria().getId() == null) {
            return ResponseEntity.badRequest().body("La categoría es obligatoria.");
        }
        Optional<Categoria> catOpt = categoriaRepository.findById(productDetails.getCategoria().getId());
        if (catOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("La categoría seleccionada no existe en la base de datos local.");
        }

        
        producto.setNombre(productDetails.getNombre().trim());
        producto.setDescripcion(productDetails.getDescripcion());
        producto.setPrecio(productDetails.getPrecio());
        producto.setStock(productDetails.getStock());
        producto.setCategoria(catOpt.get());

        productosRepository.save(producto);

        return ResponseEntity.ok(producto);
    }

}
