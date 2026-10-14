package com.storeApi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeApi.web.dto.ResponseApiDto;
import com.storeApi.web.service.CatalogoService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CatalogoController {
	
	@Autowired
	private CatalogoService catalagoServices;
	
	@PostMapping("/catalogo/import")
	public ResponseEntity<?> importarCatalogo() {
        ResponseApiDto result = catalagoServices.importarCatalogoExterno();
        if (!result.isSuccess()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
        }
        return ResponseEntity.ok(result);
    }
	

}
