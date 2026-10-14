package com.storeApi.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storeApi.web.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	Optional<Categoria> findByNombre(String nombre);

}
