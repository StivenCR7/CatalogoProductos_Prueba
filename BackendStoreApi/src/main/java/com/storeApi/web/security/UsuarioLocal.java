package com.storeApi.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.storeApi.web.model.Usuarios;
import com.storeApi.web.repository.UsuarioRepository;

@Component
public class UsuarioLocal implements CommandLineRunner{

	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    
    public void run(String...  args) throws Exception {
        
        if (usuarioRepository.findByEmail("admingg123@gmail.com").isEmpty()) {
            Usuarios admin = new Usuarios();
            admin.setEmail("admingg123@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            usuarioRepository.save(admin);
            System.out.println("Usuario de prueba creado: admingg123@gmail.com / admin123");
        }
    }
}
