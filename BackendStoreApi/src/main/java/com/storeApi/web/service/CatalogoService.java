package com.storeApi.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.storeApi.web.dto.FakeSotreApiDto;
import com.storeApi.web.dto.ResponseApiDto;
import com.storeApi.web.model.Categoria;
import com.storeApi.web.model.Productos;
import com.storeApi.web.repository.CategoriaRepository;
import com.storeApi.web.repository.ProductosRepository;

import jakarta.transaction.Transactional;

@Service
public class CatalogoService {
	
	@Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductosRepository productosRespository;

    @Transactional
    public ResponseApiDto importarCatalogoExterno() {
        int importedCount = 0;
        int skippedCount = 0;

        try {
            
            String categorasUrl = "https://fakestoreapi.com/products/categories";
            String[] externalCategorias = restTemplate.getForObject(categorasUrl, String[].class);

            if (externalCategorias != null) {
                for (String nombre : externalCategorias) {
                    
                    categoriaRepository.findByNombre(nombre).orElseGet (() -> {
                        Categoria newCat = new Categoria();
                        newCat.setNombre(nombre);
                        return categoriaRepository.save(newCat);
                    });
                }
            }

            
            String productUrl = "https://fakestoreapi.com/products";
            FakeSotreApiDto[] externalProducts = restTemplate.getForObject(productUrl, FakeSotreApiDto[].class);

            if (externalProducts != null) {
                for (FakeSotreApiDto extProd : externalProducts) {
                    Integer externalIdStr = Integer.valueOf(extProd.getId());

                    
                    boolean exists = productosRespository.existsByExternalId(externalIdStr);

                    if (exists) {
                        
                        skippedCount++;
                    } else {
                        
                        Categoria localCategory = categoriaRepository.findByNombre(extProd.getCategory())
                                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + extProd.getCategory()));

                        // Mapeo de campos
                        Productos newProduct = new Productos();
                        newProduct.setExternalId(externalIdStr);
                        newProduct.setNombre(extProd.getTitle());
                        newProduct.setDescripcion(extProd.getDescription());
                        newProduct.setPrecio(Double.valueOf(extProd.getPrice()));
                        newProduct.setStock(0); 
                        newProduct.setImagenUrl(extProd.getImage());
                        newProduct.setCategoria(localCategory);

                        productosRespository.save(newProduct);
                        importedCount++;
                    }
                }
            }

            return new ResponseApiDto(true, importedCount, skippedCount, "Importación completada con éxito.");

        } catch (Exception e) {
            
            return new ResponseApiDto(false, importedCount, skippedCount, "Error al conectar con la API externa: " + e.getMessage());
        }
    }
	
}
