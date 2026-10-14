package com.storeApi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storeApi.web.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Integer>{

	boolean existsByExternalId(Integer externalIdStr);

}
