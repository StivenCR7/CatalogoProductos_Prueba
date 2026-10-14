package com.storeApi.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeApi.web.dto.LoginRequest;
import com.storeApi.web.dto.LoginResponse;
import com.storeApi.web.model.Usuarios;
import com.storeApi.web.repository.UsuarioRepository;
import com.storeApi.web.security.JWTCreator;
import com.storeApi.web.security.JWTObject;
import com.storeApi.web.security.SecurityConfig;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private  BCryptPasswordEncoder passwordEncoder;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
		Optional<Usuarios> usuarioOp= usuarioRepository.findByEmail(request.getEmail());
		
		if(usuarioOp.isPresent()) {
			Usuarios usuario = usuarioOp.get();
			if (passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
				JWTObject jwtObject = new JWTObject();
                jwtObject.setSubject(usuario.getEmail());
                String token = JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject);
                return ResponseEntity.ok(new LoginResponse(token));
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales no validas");
	}
	
}
