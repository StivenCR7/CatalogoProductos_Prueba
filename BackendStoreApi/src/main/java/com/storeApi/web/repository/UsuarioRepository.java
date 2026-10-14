package com.storeApi.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storeApi.web.model.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
	
	 Optional<Usuarios> findByEmail(String email);

}
